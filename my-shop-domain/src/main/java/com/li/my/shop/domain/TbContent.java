package com.li.my.shop.domain;

import com.li.my.shop.commons.persistence.BaseEntity;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * @author 李洋
 * @date 2019-07-20 17:16
 */
@Data
public class TbContent extends BaseEntity {
    @Length(min = 1 , max = 20 , message = "标题长度在1-20之间")
    private String title;
    @Length(min = 1 , max = 20 , message = "子标题长度在1-20之间")
    private String subTitle;
    @Length(min = 1 , max = 50 , message = "标题内容长度在1-50之间")
    private String titleDesc;
    private String url;
    private String pic;
    private String pic2;
    @Length(min = 1 , message = "内容不能为空")
    private String content;
    @NotNull(message = "分类不能为空")
    private TbContentCategory tbContentCategory;
}
