package com.li.my.shop.web.admin.web.controller;

import com.li.my.shop.commons.ConstantUtils;
import com.li.my.shop.domain.TbUser;
import com.li.my.shop.web.admin.service.TbUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 李洋
 * @date 2019-07-15 18:56
 */
@Controller
public class LoginController {
    @Autowired
    private TbUserService tbUserService;
    @RequestMapping(value = {"" , "login"} , method = RequestMethod.GET)
    public String login(){
        return "login";
    }
    @RequestMapping(value = "login",method = RequestMethod.POST)
    public String login(@RequestParam(required = true) String email, @RequestParam(required = true)String password , HttpServletRequest httpServletRequest, Model model){
        TbUser tbUser = tbUserService.login(email, password);
        if (tbUser == null){
            //登录失败
            model.addAttribute("message","邮箱或密码有误!");
            return login();
        }
        else {
            //登录成功
            httpServletRequest.getSession().setAttribute(ConstantUtils.SESSION_USER , tbUser);
            return "redirect:main";
        }
    }


    @RequestMapping(value = "/logout" ,method = RequestMethod.GET)
    public String logout(HttpServletRequest httpServletRequest){
        httpServletRequest.getSession().removeAttribute(ConstantUtils.SESSION_USER);
        httpServletRequest.getSession().invalidate();
        return "redirect:/login";

    }
}
