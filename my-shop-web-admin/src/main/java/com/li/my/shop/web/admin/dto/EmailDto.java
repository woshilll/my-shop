package com.li.my.shop.web.admin.dto;

import com.li.my.shop.commons.utils.RegexpUtils;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Pattern;

/**
 * @author 李洋
 * @date 2019-07-31 18:52
 */
@Data
public class EmailDto {
    @Pattern(regexp = RegexpUtils.EMAIL , message = "请输入正确的邮箱")
    private String to;
    @Length(min = 1 , max = 50 , message = "请输入邮件主题")
    private String subTitle;
    private String content;
}
