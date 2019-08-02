package com.li.my.shop.web.api.service;

import com.li.my.shop.domain.TbContent;

import java.util.List;

/**
 * @author 李洋
 * @date 2019-07-27 15:21
 */
public interface TbContentService {
    /**
     * 通过category id查找
     * @param categoryId
     * @return
     */
    List<TbContent> findByCategoryId(Long categoryId);
}
