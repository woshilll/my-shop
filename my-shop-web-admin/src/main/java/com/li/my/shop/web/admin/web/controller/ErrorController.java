package com.li.my.shop.web.admin.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author 李洋
 * @date 2019-08-04 10:08
 */
@Controller
@RequestMapping(value = "error")
public class ErrorController {
    @RequestMapping(value = "404" , method = RequestMethod.GET)
    public String error_404(){
        return "404";
    }
}
