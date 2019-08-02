package com.li.my.shop.web.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

/**
 * @author 李洋
 * @date 2019-07-29 10:22
 */
@Data
public class TbUserDto {
    private Long id;
    private String username;
    @JsonIgnore
    private String password;
    private String phone;
    private String email;
}
