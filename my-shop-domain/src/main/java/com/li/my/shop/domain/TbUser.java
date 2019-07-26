package com.li.my.shop.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.li.my.shop.commons.persistence.BaseEntity;
import com.li.my.shop.commons.utils.RegexpUtils;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Pattern;


/**
 * @author 李洋
 * @date 2019-07-16 12:25
 */
@Data
public class TbUser extends BaseEntity {
    @Length(min = 6 , max = 20 , message = "用户名需在6-20之间" )
    private String username;
    @JsonIgnore
    private String password;
    @Pattern(regexp = RegexpUtils.PHONE , message = "手机格式不正确")
    private String phone;
    @Pattern(regexp = RegexpUtils.EMAIL , message = "邮箱格式不正确")
    private String email;



}
