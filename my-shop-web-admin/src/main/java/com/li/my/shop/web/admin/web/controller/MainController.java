package com.li.my.shop.web.admin.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author 李洋
 * @date 2019-07-15 19:09
 */
@Controller
public class MainController {
    @RequestMapping(value = "main" , method = RequestMethod.GET)
    public String main(){
        return "main";
    }
}
