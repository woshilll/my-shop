package com.li.my.shop.web.admin.web.controller;

import com.li.my.shop.commons.ConstantUtils;
import com.li.my.shop.commons.utils.CookieUtils;
import com.li.my.shop.domain.TbUser;
import com.li.my.shop.web.admin.service.TbUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 李洋
 * @date 2019-07-15 18:56
 */
@Controller
public class LoginController {
    @Autowired
    private TbUserService tbUserService;
    @RequestMapping(value = {"" , "login"} , method = RequestMethod.GET)
    public String login(HttpServletRequest httpServletRequest){
        //得到cookie
        String isRemember = CookieUtils.getCookieValue(httpServletRequest, "isRemember");
        if (StringUtils.isNotBlank(isRemember)){
            String[] userInfo = isRemember.split(":");
            String email = userInfo[0];
            String password = userInfo[1];
            httpServletRequest.setAttribute("email" , email);
            httpServletRequest.setAttribute("password" , password);
            httpServletRequest.setAttribute("isRemember" , true);
        }
        return "login";
    }
    @RequestMapping(value = "login",method = RequestMethod.POST)
    public String login(@RequestParam(required = true) String email, @RequestParam(required = true)String password ,
                        String isRemember, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Model model){
        TbUser tbUser = tbUserService.login(email, password);
        //没有选中记住我
        if (isRemember == null){
            CookieUtils.deleteCookie(httpServletRequest , httpServletResponse , "isRemember");
        }
        if (tbUser == null){
            //登录失败
            model.addAttribute("message","邮箱或密码有误!");
            return login(httpServletRequest);
        }
        else {
            //登录成功
            //记住我功能
            if (isRemember != null){
                //设置cookie
                CookieUtils.setCookie(httpServletRequest , httpServletResponse , "isRemember" , String.format("%s:%s",email , password));
            }

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
