package com.li.my.shop.commons.persistence;

import com.li.my.shop.commons.dto.BaseResult;

import java.util.List;

/**
 * @author 李洋
 * @date 2019-07-25 17:06
 */
public interface BaseTreeService<T extends BaseEntity> {
    /**
     * 查询所有信息
     * @return
     */
    List<T> selectAll();


    /**
     * 新增或则更新信息
     * @param entity
     * @return
     */
    BaseResult save(T entity);

    /**
     * 通过id获取
     * @param id
     * @return
     */
    T getById(Long id);


    /**
     * 通过父id查询分类信息
     * @param parentId
     * @return
     */
    List<T> selectByParentId(Long parentId);
}
