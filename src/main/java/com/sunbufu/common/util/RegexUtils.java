package com.sunbufu.common.util;

import com.sunbufu.common.PageInfo;
import com.sunbufu.common.PageList;
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
     * jd的html页码信息正则表达式
     */
    public static final String jdHtmlPageInfoPatternStr = "[\\S]*SEARCH.adv_param=\\{page:\"([\\d]+)\",page_count:\"([\\d]+)\",psort:[\\S]*";
    /**
     * 是否是url的正则表达式
     */
    public static final String urlPatternStr = "^[a-zA-z]+://[^\\s]*$";
    /**
     * 是否是数字的正则表达式
     */
    public static final String numberPatternStr = "^[1-9]\\d*$";

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

    /**
     * 从jd的html中获取页码信息
     *
     * @param jdHtml
     * @return
     */
    public static PageInfo getPageInfoFromJdHtml(String jdHtml) {
        if (!StringUtils.isEmpty(jdHtml)) {
            Pattern pattern = Pattern.compile(jdHtmlPageInfoPatternStr);
            Matcher matcher = pattern.matcher(jdHtml);
            if (matcher.find()) {
                return new PageInfo(Integer.valueOf(matcher.group(1)), Integer.valueOf(matcher.group(2)));
            }
        }
        return new PageInfo();
    }

    /**
     * 判断是不是url
     *
     * @param str
     * @return
     */
    public static boolean isUrl(String str) {
        if (!StringUtils.isEmpty(str)) {
            return Pattern.matches(urlPatternStr, str);
        }
        return false;
    }

    /**
     * 判断是不是纯数字
     *
     * @param str
     * @return
     */
    public static boolean isNumber(String str) {
        if (!StringUtils.isEmpty(str)) {
            return Pattern.matches(numberPatternStr, str);
        }
        return false;
    }
}
