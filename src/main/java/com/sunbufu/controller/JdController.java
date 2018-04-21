package com.sunbufu.controller;

import com.sunbufu.common.exception.ServiceException;
import com.sunbufu.common.util.RegexUtils;
import com.sunbufu.service.JdService;
import io.swagger.annotations.ApiOperation;
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
public class JdController {

    @Autowired
    JdService jdService;

    @ApiOperation("根据jdUrl查询")
    @RequestMapping("searchByJdUrl")
    public String searchByJdUrl(String jdUrl, Model model) {
        if (StringUtils.isEmpty(jdUrl)) {
            model.addAttribute("msg", "请输入jd商品链接");
            return "error";
        }
        try {
            double price = jdService.searchByJdUrl(jdUrl);
            model.addAttribute("msg", price);
            return "result";
        } catch (ServiceException e) {
            model.addAttribute("msg", e.getMessage());
            return "error";
        }
    }

    @ApiOperation("根据skuId查询")
    @RequestMapping("searchBySkuId")
    public String searchByJdSkuId(String skuId, Model model) {
        if (StringUtils.isEmpty(skuId)) {
            model.addAttribute("msg", "请输入jd商品链接");
            return "error";
        }
        try {
            model.addAttribute("msg", jdService.searchBySkuId(skuId));
            return "result";
        } catch (ServiceException e) {
            model.addAttribute("msg", e.getMessage());
            return "error";
        }

    }

}
