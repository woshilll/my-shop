package com.li.my.shop.commons.persistence;

import lombok.Data;

/**
 * @author 李洋
 * @date 2019-07-26 09:55
 */
@Data
public class BaseTreeEntity<T extends BaseEntity> extends BaseEntity {
    private T parent;
    private Boolean isParent;
}
