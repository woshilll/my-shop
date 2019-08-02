package com.li.my.shop.web.ui.web.controller;

import com.google.code.kaptcha.Constants;
import com.li.my.shop.commons.dto.BaseResult;
import com.li.my.shop.web.ui.api.UserApi;
import com.li.my.shop.web.ui.constant.SystemConstant;
import com.li.my.shop.web.ui.dto.TbUser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 李洋
 * @date 2019-07-29 10:27
 */
@Controller
public class LoginController {


    @RequestMapping(value = "login" , method = RequestMethod.GET)
    public String login(){
        return "login";
    }

    @RequestMapping(value = "login" , method = RequestMethod.POST)
    public String login(TbUser tbUser , Model model , HttpServletRequest request) throws Exception {
        if (!checkVerification(tbUser , request)){
            model.addAttribute("baseResult" , BaseResult.fail("验证码错误，请重新输入"));
            return login();
        }

        TbUser user = UserApi.login(tbUser);
        if (user == null){
            model.addAttribute("baseResult" , BaseResult.fail("用户名或密码错误！请重新输入"));
            return login();
        }else {
            request.getSession().setAttribute(SystemConstant.SESSION_USER_KEY, user);
            return "redirect:/index";
        }
    }


    @RequestMapping(value = "logout" , method = RequestMethod.GET)
    public String logout(HttpServletRequest request){
        request.getSession().invalidate();
        return "redirect:/index";
    }

    private Boolean checkVerification(TbUser tbUser , HttpServletRequest request){
        String verification = (String) request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
        if (StringUtils.equals(verification , tbUser.getVerification())){
            return true;
        }
        return false;
    }
}
