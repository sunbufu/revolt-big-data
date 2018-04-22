package com.sunbufu.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sunbufu.common.PageInfo;
import com.sunbufu.common.PageList;
import com.sunbufu.common.exception.ServiceException;
import com.sunbufu.common.util.RegexUtils;
import com.sunbufu.dto.JdPriceDTO;
import com.sunbufu.dto.JdSkuDTO;
import com.sunbufu.service.JdService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * jd服务实现类
 *
 * @author sunbufu
 */
@Service("jdService")
public class JdServiceImpl implements JdService {

    private static final Logger log = LoggerFactory.getLogger(JdServiceImpl.class);

    @Value("${url.jd.priceBySkuId}")
    private String priceBySkuIdUrl;
    @Value("${url.jd.skuIdByKeyword}")
    private String skuIdByKeywordUrl;
    @Value("${url.jd.itemUrl}")
    private String itemUrl;

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public JdSkuDTO searchByJdUrl(String jdUrl) throws ServiceException {
        log.info("查询 jdUrl={} 的价格", jdUrl);
        String skuId = RegexUtils.getSkuIdFromJDUrl(jdUrl);
        if (StringUtils.isEmpty(skuId)) {
            throw new ServiceException("请输入正确的jd商品链接");
        }
        return searchBySkuId(skuId);
    }

    @Override
    public JdSkuDTO searchBySkuId(String skuId) throws ServiceException {
        log.info("查询 skuId={} 的价格", skuId);
        String skuIdWithPrefix = JdPriceDTO.JD_SKU_ID_PREFIX + skuId;
        try {
            Map<String, JdPriceDTO> jdPriceMap = searchBySkuIds(Collections.singletonList(skuIdWithPrefix));
            log.info("url = [{}]", itemUrl + skuId + ".html");
            ResponseEntity<String> response = restTemplate.getForEntity(itemUrl + skuId + ".html", String.class);
            Document doc = Jsoup.parse(response.getBody());
            String img = doc.select("img[id=spec-img]").attr("data-origin");
            String title = doc.select("div[class=sku-name]").text();
            return new JdSkuDTO(skuId, title, img, jdPriceMap.get(skuIdWithPrefix).getPrice());
        } catch (Exception e) {
            log.warn("searchBySkuId skuId={} 失败", skuId);
            e.printStackTrace();
            throw new ServiceException("skuId异常");
        }
    }

    @Override
    public PageList<JdSkuDTO> searchByKeyword(String keyword, int pageNum) throws ServiceException {
        PageList<JdSkuDTO> jdSkuList = new PageList<>();
        //jd的页面都是奇数（不知道为什么）
        pageNum = pageNum * 2 - 1;
        try {
            ResponseEntity<String> response = restTemplate.getForEntity(skuIdByKeywordUrl + keyword + "&page=" + pageNum, String.class);
            Document doc = Jsoup.parse(response.getBody());
            //商品信息
            Elements elements = doc.select(".gl-item");
            for (Element element : elements) {
                String skuId = element.attr("data-sku");
                String title = element.selectFirst("a[target=_blank]").attr("title");
                //处理图片地址（防止懒加载图片造成的地址无效）
                Element imgEle = element.selectFirst("img");
                String img = imgEle.attr("data-lazy-img");
                if (StringUtils.isEmpty(img) || img.equals("done")) {
                    img = imgEle.attr("src");
                }
                String price = element.select("." + JdPriceDTO.JD_SKU_ID_PREFIX + skuId + " i").text();
                jdSkuList.add(new JdSkuDTO(skuId, title, img, price));
            }
            //分页信息
            PageInfo pageInfo = RegexUtils.getPageInfoFromJdHtml(response.getBody());
            jdSkuList.setPageInfo(pageInfo);
        } catch (Exception e) {
            log.warn("searchByKeyword keyword={} 失败", keyword);
            e.printStackTrace();
            throw new ServiceException("keyword异常");
        }
        return jdSkuList;
    }

    /***
     * 请求skuIds对应价格
     *
     * @param skuIds with "JD_SKU_ID_PREFIX"
     * @return map<skuId ,   JdPriceDTO> nullable
     */
    private Map<String, JdPriceDTO> searchBySkuIds(List<String> skuIds) throws IOException {
        Map<String, JdPriceDTO> skuMap;
        //将skuIds转换成请求样式
        String skuIdsStr = (skuIds.stream().reduce((result, skuId) -> result + ("," + skuId)).get());
        ResponseEntity<String> response = restTemplate.getForEntity(priceBySkuIdUrl + "?skuIds=" + skuIdsStr, String.class);
        List<JdPriceDTO> jdPriceList = objectMapper.readValue(response.getBody(), new TypeReference<List<JdPriceDTO>>() {
        });
        //将jdPriceList转换成map<skuId, JdPriceDTO>
        skuMap = jdPriceList.stream().collect(Collectors.toMap(JdPriceDTO::getSkuId, Function.identity()));
        return skuMap;
    }

}
