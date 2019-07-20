package com.li.my.shop.web.admin.dao;

import com.li.my.shop.domain.TbUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author 李洋
 * @date 2019-07-16 12:27
 */
@Repository
public interface TbUserDao {
    /**
     * 查询所有的用户信息
     * @return
     */
    List<TbUser> selectAll();

    /**
     * 通过邮箱查找用户
     * @param email
     * @return
     */
    TbUser getByEmail(String email);

    /**
     * 插入用户信息
     * @param tbUser
     */
    void insert(TbUser tbUser);

    /**
     * 更新用户信息
     * @param tbUser
     */
    void updateById(TbUser tbUser);

    /**
     * 通过id获取用户
     * @param id
     * @return
     */
    TbUser getById(Long id);

    /**
     * 批量删除
     * @param ids
     */
    void batchDelete(String[] ids);

    /**
     * 查询用户总数
     * @param tbUser
     * @return
     */
    int count(TbUser tbUser);

    /**
     * 分页查询
     * @param params start/起始位置  length/每页长度
     * @return
     */
    List<TbUser> page(Map<String , Object> params);
}
