package com.sunbufu.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sunbufu.common.exception.ServiceException;
import com.sunbufu.common.util.RegexUtils;
import com.sunbufu.service.JdService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * jd服务实现类
 *
 * @author sunbufu
 */
@Service("jdService")
public class JdServiceImpl implements JdService {

    Logger log = LoggerFactory.getLogger(JdServiceImpl.class);

    @Value("${url.jd.priceBySkuid}")
    private String priceUrl;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public double searchByJdUrl(String jdUrl) throws ServiceException {
        String skuId = RegexUtils.getSkuIdFromJDUrl(jdUrl);
        if(StringUtils.isEmpty(skuId)){
            throw new ServiceException("请输入正确的jd商品链接");
        }
        return searchBySkuId(skuId);
    }

    @Override
    public double searchBySkuId(String skuId) throws ServiceException {
        log.info("查询 skuId={} 的价格", skuId);
        double price = -1;
        try {
            ResponseEntity<String> response = restTemplate.getForEntity(priceUrl + "?skuIds=J_" + skuId, String.class);
            List<Map<String, String>> list = new ObjectMapper().readValue(response.getBody(), List.class);
            price = Double.parseDouble(list.get(0).get("p"));
        } catch (IOException e) {
            log.warn("searchBySkuId skuId={} 失败", skuId);
            e.printStackTrace();
            throw new ServiceException("skuId异常");
        }
        return price;
    }

}
