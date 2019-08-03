package com.li.my.shop.web.admin.web.controller;

import com.li.my.shop.commons.dto.BaseResult;
import com.li.my.shop.commons.utils.CookieUtils;
import com.li.my.shop.commons.utils.EmailSendUtils;
import com.li.my.shop.web.admin.service.TbUserService;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.mail.EmailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 李洋
 * @date 2019-08-03 19:47
 */
@Controller
@RequestMapping(value = "forget")
public class ForgetController {


    @Autowired
    private EmailSendUtils emailSendUtils;
    @Autowired
    private TbUserService tbUserService;
    @RequestMapping(value = "password" , method = RequestMethod.GET)
    public String forgetPassword(){
        return "forget_password";
    }

    @ResponseBody
    @RequestMapping(value = "sendEmail" , method = RequestMethod.GET)
    public BaseResult sendEmail(String email , HttpServletRequest request) throws EmailException {
        String sum = (int) ((Math.random()*9+1)*100000)+"";
        emailSendUtils.sendSimpleEmail("重置密码" , "验证码为："+sum , email);
        request.getSession().setAttribute("forgetCode" , sum);
        return BaseResult.success("成功",sum);
    }

    @RequestMapping(value = "changePwd" , method = RequestMethod.POST)
    public String changePwd(String email , String password , String yzm , HttpServletRequest request , HttpServletResponse response){
        String forgetCode = (String) request.getSession().getAttribute("forgetCode");
        if (StringUtils.isNotBlank(yzm) && StringUtils.isNotBlank(forgetCode)){
            if (yzm.equals(forgetCode)){
                password = DigestUtils.md5DigestAsHex(password.getBytes());
                tbUserService.changePwd(email , password);
                CookieUtils.deleteCookie(request , response , "isRemember");
                return "redirect:/login";
            }
        }
        return forgetPassword();
    }
}
