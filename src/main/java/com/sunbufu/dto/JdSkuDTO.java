package com.sunbufu.dto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * jd商品列表
 *
 * @author sunbufu
 */
public class JdSkuDTO {

    private static final Logger log = LoggerFactory.getLogger(JdSkuDTO.class);

    /**jd地址的协议*/
    public static final String JD_URL_PREFIX = "https:";

    /**jd商品url前缀*/
    public static final String JD_ITEM_URL_PREFIX = JD_URL_PREFIX + "//item.jd.com/";
    /**jd商品url后缀*/
    public static final String JD_ITEM_URL_SUFFIX = ".html";

    private String skuId;
    private String title;
    private String url;
    private String img;
    private double price;

    public JdSkuDTO() {
    }

    public JdSkuDTO(String skuId, String title, String img, String price) {
        this.skuId = skuId;
        this.title = title;
        this.img = img;
        try {
            this.price = Double.valueOf(price);
        }catch (Exception e){
            this.price = -1;
            log.warn("价格转换失败 price={}", price);
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "JdSkuDTO{" +
                "skuId='" + getSkuId() + '\'' +
                ", title='" + getTitle() + '\'' +
                ", url='" + getUrl() + '\'' +
                ", img='" + getImg() + '\'' +
                ", price=" + getPrice() +
                '}';
    }

//------GETTER/SETTER

    public String getSkuId() {
        return skuId;
    }

    public void setSkuId(String skuId) {
        this.skuId = skuId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return JD_ITEM_URL_PREFIX + skuId + JD_ITEM_URL_SUFFIX;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImg() {
        return JD_URL_PREFIX + img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
