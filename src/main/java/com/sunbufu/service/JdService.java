package com.sunbufu.service;

import com.sunbufu.common.PageList;
import com.sunbufu.common.exception.ServiceException;
import com.sunbufu.dto.JdSkuDTO;

/**
 * JD服务
 *
 * @author sunbufu
 */
public interface JdService {

    /**
     * 根据商品url查询商品价格
     * @param jdUrl
     * @return
     */
    double searchByJdUrl(String jdUrl) throws ServiceException;

    /**
     * 根据skuId查询商品价格
     *
     * @param skuId
     * @return
     */
    double searchBySkuId(String skuId) throws ServiceException;

    /**
     * 根据关键字查询商品
     *
     * @param keyword
     * @param pageNum
     * @return
     */
    PageList<JdSkuDTO> searchByKeyword(String keyword, int pageNum) throws ServiceException;
}
