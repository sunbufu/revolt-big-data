package com.sunbufu.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * jd价格返回
 *
 * @author sunbufu
 */
public class JdPriceDTO {

    /**
     * 京东skuId的前缀
     */
    public static final String JD_SKU_ID_PREFIX = "J_";

    @JsonProperty("id")
    private String skuId;
    private int skuIdInt;
    @JsonProperty("p")
    private String price;

    private String op;
    private String m;

    //-----------GETTER/SETTER

    public int getSkuIdInt() {
        if(skuIdInt == 0 && skuId != null){
            skuIdInt = Integer.valueOf(skuId.replace(JD_SKU_ID_PREFIX, ""));
        }
        return skuIdInt;
    }

    public String getSkuId() {
        if(skuId == null && skuIdInt != 0){
            skuId = JD_SKU_ID_PREFIX + skuIdInt;
        }
        return skuId;
    }

    public void setSkuIdInt(int skuIdInt) {
        this.skuIdInt = skuIdInt;
    }

    public void setSkuId(String skuId) {
        this.skuId = skuId;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getOp() {
        return op;
    }

    public void setOp(String op) {
        this.op = op;
    }

    public String getM() {
        return m;
    }

    public void setM(String m) {
        this.m = m;
    }
}
