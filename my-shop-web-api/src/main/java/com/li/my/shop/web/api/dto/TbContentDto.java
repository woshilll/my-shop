package com.li.my.shop.web.api.dto;

import lombok.Data;

/**
 * @author 李洋
 * @date 2019-07-28 10:49
 */
@Data
public class TbContentDto {
    private Long id;
    private String title;
    private String subTitle;
    private String titleDesc;
    private String url;
    private String pic;
    private String pic2;
}
