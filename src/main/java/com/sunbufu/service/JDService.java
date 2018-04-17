package com.sunbufu.service;

/**
 * JD服务
 *
 * @author sunbufu
 */
public interface JDService {

    /**
     * 根据skuId查询商品价格
     *
     * @param skuId
     * @return
     */
    double searchBySkuId(String skuId);

}
