package com.li.my.shop.commons.persistence;

import com.li.my.shop.commons.dto.BaseResult;
import com.li.my.shop.commons.dto.PageInfo;

import java.util.List;

/**
 * @author 李洋
 * @date 2019-07-23 16:03
 */
public interface BaseService<T extends BaseEntity> {
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
     * @param start 起始位置
     * @param length 大小
     * @param draw
     * @param entity
     * @return
     */
    PageInfo<T> page(int start , int length , int draw , T entity);
}
