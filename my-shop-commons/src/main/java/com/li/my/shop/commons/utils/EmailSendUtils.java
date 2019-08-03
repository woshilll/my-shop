package com.li.my.shop.commons.utils;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author 李洋
 * @date 2019-07-30 09:51
 */
public class EmailSendUtils {
    @Autowired
    Email email;
    @Autowired
    HtmlEmail htmlEmail;
    public void sendSimpleEmail(String subject , String msg , String... to) throws EmailException {
        email.setSubject(subject);
        email.setMsg(msg);
        email.addTo(to);
        email.send();
    }

    public void sendHtmlEmail(String subject , String htmlMsg , String... to) throws EmailException {
        htmlEmail.setSubject(subject);
        htmlEmail.setHtmlMsg(htmlMsg);
        htmlEmail.addTo(to);
        htmlEmail.send();
    }
}
