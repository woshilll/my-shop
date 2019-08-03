package com.li.my.shop.web.admin.web.controller;

import com.li.my.shop.commons.utils.EmailSendUtils;
import com.li.my.shop.web.admin.dto.EmailDto;
import org.apache.commons.mail.EmailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author 李洋
 * @date 2019-07-31 17:51
 */
@Controller
@RequestMapping(value = "email")
public class EmailController {
    @Autowired
    EmailSendUtils emailSendUtils;

    @RequestMapping(method = RequestMethod.GET)
    public String email(){
        return "email";
    }

    @RequestMapping(value = "send" , method = RequestMethod.POST)
    public String send(EmailDto emailDto) throws EmailException {
        emailSendUtils.sendHtmlEmail(emailDto.getSubTitle() , emailDto.getContent() , emailDto.getTo());
        return "email";
    }
}
