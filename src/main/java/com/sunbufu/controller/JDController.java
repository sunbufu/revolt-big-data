package com.sunbufu.controller;

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
     * @param skuId
     * @param model
     * @return
     */
    @RequestMapping("searchBySkuId")
    public String searchBySkuId(String skuId, Model model) {
        if (StringUtils.isEmpty(skuId)) {
            model.addAttribute("msg", "skuId不能为空");
            return "error";
        }
        double price = jdService.searchBySkuId(skuId);
        model.addAttribute("msg", price);
        return "result";
    }

}
