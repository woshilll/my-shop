package com.li.my.shop.web.ui.dto;

import com.li.my.shop.commons.utils.RegexpUtils;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Pattern;

/**
 * @author 李洋
 * @date 2019-07-29 10:33
 */
@Data
public class TbUser {
    private Long id;
    @Length(min = 4 , max = 16 , message = "用户名的长度在4-16之间")
    private String username;
    @Length(min = 6 , max = 16 , message = "密码长度在6-16之间")
    private String password;
    @Pattern(regexp = RegexpUtils.PHONE , message = "手机格式不正确")
    private String phone;
    @Pattern(regexp = RegexpUtils.EMAIL , message = "邮箱格式不正确")
    private String email;
    private String verification;
}
