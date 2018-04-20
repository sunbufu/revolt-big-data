package com.sunbufu.common;

import org.springframework.util.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则表达式工具类
 *
 * @author sunbufu
 */
public class RegexUtils {

    /**
     * jd商品链接正则表达式
     */
    public static final String jdItemPatternStr = "[\\S]*item.jd.com/([\\d]+).html[\\S]*";

    /**
     * 从jd的商品链接中获取skuid
     *
     * @param jdUrl
     */
    public static String getSkuIdFromJDUrl(String jdUrl) {
        if (!StringUtils.isEmpty(jdUrl)) {
            Pattern pattern = Pattern.compile(jdItemPatternStr);
            Matcher matcher = pattern.matcher(jdUrl);
            if (matcher.find()) {
                return matcher.group(1);
            }
        }
        return "";
    }

}
