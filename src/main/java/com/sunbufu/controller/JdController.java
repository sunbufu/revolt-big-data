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

    /**
     * 分发请求：
     * <p>
     * 如果是url，取skuId搜索
     * 如果是数字，按skuIf搜索
     * 其他，按关键字搜索
     *
     * @param key
     * @param pageNum
     * @param model
     * @return
     */
    @ApiOperation("分发请求")
    @RequestMapping("search")
    public String search(String key, Integer pageNum, Model model) {
        if (StringUtils.isEmpty(key)) {
            model.addAttribute("msg", "请输入关键字");
            return "error";
        }
        if (RegexUtils.isUrl(key)) {
            return searchByJdUrl(key, model);
        } else if (RegexUtils.isNumber(key)) {
            return searchByJdSkuId(key, model);
        } else {
            return searchByJdKeyword(key, pageNum, model);
        }
    }

    @ApiOperation("根据jdUrl查询")
    @RequestMapping("searchByJdUrl")
    public String searchByJdUrl(String jdUrl, Model model) {
        if (StringUtils.isEmpty(jdUrl)) {
            model.addAttribute("msg", "请输入jd商品链接");
            return "error";
        }
        try {
            model.addAttribute("sku", jdService.searchByJdUrl(jdUrl));
            return "single_result";
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
            model.addAttribute("sku", jdService.searchBySkuId(skuId));
            return "single_result";
        } catch (ServiceException e) {
            model.addAttribute("msg", e.getMessage());
            return "error";
        }
    }

    @ApiOperation("根据关键字查询")
    @RequestMapping("searchByKeyword")
    public String searchByJdKeyword(String keyword, Integer pageNum, Model model) {
        if (StringUtils.isEmpty(keyword)) {
            model.addAttribute("msg", "请输入关键字");
            return "error";
        }
        if (pageNum == null) {
            pageNum = 1;
        }
        try {
            model.addAttribute("pageList", jdService.searchByKeyword(keyword, pageNum));
            return "result";
        } catch (ServiceException e) {
            model.addAttribute("msg", e.getMessage());
            return "error";
        }
    }

}
