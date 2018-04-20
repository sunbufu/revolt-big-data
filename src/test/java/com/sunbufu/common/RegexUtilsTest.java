package com.sunbufu.common;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class RegexUtilsTest {
    @Test
    public void getSkuIdFromJDUrl() throws Exception {
        List<String> urls = new ArrayList<>(2);
        urls.add("https://item.jd.com/21114666049.html");
        urls.add("https://item.jd.c2/21114666049.html");

        List<String> skuIds = new ArrayList<>(2);
        skuIds.add("21114666049");
        skuIds.add("");

        for(int i = 0; i < urls.size(); i++){
            assertEquals(skuIds.get(i), RegexUtils.getSkuIdFromJDUrl(urls.get(i)));
        }
    }

}