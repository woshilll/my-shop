package com.li.my.shop.commons.utils;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author 李洋
 * @date 2019-07-30 09:51
 */
public class EmailSendUtils {
    @Autowired
    Email email;
    public void sendSimpleEmail(String subject , String msg , String... to) throws EmailException {
        email.setSubject(subject);
        email.setMsg(msg);
        email.addTo(to);
        email.send();
    }
}
