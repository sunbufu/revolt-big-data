package com.sunbufu.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 主页接口
 *
 * @author sunbufu
 */
@Api(tags = {"主页"})
@Controller
public class HomeController {

    @ApiOperation("主页")
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String root() {
        return "index";
    }

    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String index(Model model) {
        return "redirect:/";
    }

}
