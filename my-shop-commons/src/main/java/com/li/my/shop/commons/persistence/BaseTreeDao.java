package com.li.my.shop.commons.persistence;

import java.util.List;

/**
 * 树结构所需dao
 * @author 李洋
 * @date 2019-07-25 17:05
 */
public interface BaseTreeDao<T extends BaseEntity> {
    /**
     * 查询所有的信息
     * @return
     */
    List<T> selectAll();


    /**
     * 插入信息
     * @param entity
     */
    void insert(T entity);

    /**
     * 更新信息
     * @param entity
     */
    void updateById(T entity);

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
