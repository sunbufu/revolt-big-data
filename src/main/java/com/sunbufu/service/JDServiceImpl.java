package com.sunbufu.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service("jdService")
public class JDServiceImpl implements JDService {

    Logger log = LoggerFactory.getLogger(JDServiceImpl.class);

    @Value("${url.jd.priceBySkuid}")
    private String priceUrl;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public double searchBySkuId(String skuId) {
        log.info("查询 skuId={} 的价格", skuId);
        double price = -1;
        try {
            ResponseEntity<String> response = restTemplate.getForEntity(priceUrl + "?skuIds=J_" + skuId, String.class);
            List<Map<String, String>> list = new ObjectMapper().readValue(response.getBody(), List.class);
            price = Double.parseDouble(list.get(0).get("p"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return price;
    }

}
