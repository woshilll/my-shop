package com.li.my.shop.web.api.dao;

import com.li.my.shop.domain.TbUser;
import org.springframework.stereotype.Repository;

/**
 * @author 李洋
 * @date 2019-07-28 18:21
 */
@Repository
public interface TbUserDao {
    /**
     * 登录判定
     * @param tbUser
     * @return
     */
    TbUser login(TbUser tbUser);

    /**
     * 注册前检查，判断用户名、邮箱、手机是否存在
     * @param tbUser
     * @return
     */
    int checkRegister(TbUser tbUser);

    /**
     * 用户注册，成功返回1，失败返回0
     * @param tbUser
     * @return
     */
    int register(TbUser tbUser);
}
