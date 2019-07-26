package com.li.my.shop.domain;

import com.li.my.shop.commons.persistence.BaseEntity;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * @author 李洋
 * @date 2019-07-20 11:00
 */
@Data
public class TbContentCategory extends BaseEntity {
    @Length(min = 1 , max = 20 , message = "分类名须在 1-20 之间")
    private String name;
    private Integer status;
    @NotNull(message = "排序不能为空")
    private Integer sortOrder;
    private Boolean isParent;
    private TbContentCategory parent;

}
