package com.li.my.shop.commons.dto;

import com.li.my.shop.commons.persistence.BaseEntity;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author 李洋
 * @date 2019-07-18 21:05
 */
@Data
public class PageInfo<T extends BaseEntity> implements Serializable {
    private int draw;
    private int start;
    private int length;
    private int recordsTotal;
    private int recordsFiltered;
    private List<T> data;
    private String error;
}
