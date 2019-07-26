package com.li.my.shop.commons.persistence;

import java.util.List;
import java.util.Map;

/**
 * @author 李洋
 * @date 2019-07-23 15:57
 */
public interface BaseDao<T extends BaseEntity> {
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
     * 批量删除
     * @param ids
     */
    void batchDelete(String[] ids);

    /**
     * 查询总数
     * @param entity
     * @return
     */
    int count(T entity);

    /**
     * 分页查询
     * @param params start/起始位置  length/每页长度
     * @return
     */
    List<T> page(Map<String , Object> params);
}
