package com.li.my.shop.web.ui.web.controller;

import com.li.my.shop.commons.dto.BaseResult;
import com.li.my.shop.commons.validator.BeanValidator;
import com.li.my.shop.web.ui.api.ContentsApi;
import com.li.my.shop.web.ui.api.UserApi;
import com.li.my.shop.web.ui.constant.SystemConstant;
import com.li.my.shop.web.ui.dto.TbUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 李洋
 * @date 2019-07-29 11:36
 */
@Controller
public class RegisterController {
    @RequestMapping(value = "/register" , method = RequestMethod.GET)
    public String register(){
        return "register";
    }

    @RequestMapping(value = "/register" , method = RequestMethod.POST)
    public String register(TbUser tbUser , Model model , HttpServletRequest request) throws Exception {
        String validator = BeanValidator.validator(TbUser.class);
        //后端验证，不符合条件返回注册页
        if (validator != null){
            model.addAttribute("message" , validator);
            return register();
        }
        else {
            BaseResult baseResult = UserApi.register(tbUser);
            //注册成功
            if (baseResult.getStatus() == BaseResult.STATUS_SUCCESS){
                //将密码设为空
                tbUser.setPassword(null);
                //放入会话
                request.getSession().setAttribute(SystemConstant.SESSION_USER_KEY , tbUser);
                return "redirect:/index";
            }else {
                //注册出现异常失败
                model.addAttribute("message" , baseResult.getMessage());
                return register();
            }
        }
    }
}
