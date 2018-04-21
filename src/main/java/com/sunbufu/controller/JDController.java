package com.sunbufu.controller;

import com.sunbufu.common.util.RegexUtils;
import com.sunbufu.service.JDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * jd的接口
 *
 * @author sunbufu
 */
@Controller
@RequestMapping("jd")
public class JDController {

    @Autowired
    JDService jdService;

    /**
     * 根据skuId查询价格
     *
     * @param jdUrl
     * @param model
     * @return
     */
    @RequestMapping("searchBySkuId")
    public String searchBySkuId(String jdUrl, Model model) {
        if (StringUtils.isEmpty(jdUrl)) {
            model.addAttribute("msg", "请输入jd商品链接");
            return "error";
        }
        String skuId = RegexUtils.getSkuIdFromJDUrl(jdUrl);
        if(StringUtils.isEmpty(skuId)){
            model.addAttribute("msg", "请输入正确的jd商品链接");
            return "error";
        }
        double price = jdService.searchBySkuId(skuId);
        model.addAttribute("msg", price);
        return "result";
    }

}
