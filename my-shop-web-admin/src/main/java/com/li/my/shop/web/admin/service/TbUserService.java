package com.li.my.shop.web.admin.service;

import com.li.my.shop.commons.dto.BaseResult;
import com.li.my.shop.domain.PageInfo;
import com.li.my.shop.domain.TbUser;

import java.util.List;

/**
 * @author 李洋
 * @date 2019-07-16 12:28
 */
public interface TbUserService {
    /**
     * 查询所有用户信息
     * @return
     */
    List<TbUser> selectAll();

    /**
     * 登录功能
     * @param email
     * @param password
     * @return
     */
    TbUser login(String email , String password);

    /**
     * 新增或则更新用户信息
     * @param tbUser
     * @return
     */
    BaseResult save(TbUser tbUser);

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
     * @param start 起始位置
     * @param length 大小
     * @param draw
     * @param tbUser
     * @return
     */
    PageInfo<TbUser> page(int start , int length ,int draw , TbUser tbUser);
}
