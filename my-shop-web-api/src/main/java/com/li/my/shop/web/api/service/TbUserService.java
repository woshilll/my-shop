package com.li.my.shop.web.api.service;

import com.li.my.shop.commons.dto.BaseResult;
import com.li.my.shop.domain.TbUser;

/**
 * @author 李洋
 * @date 2019-07-28 18:25
 */
public interface TbUserService {
    /**
     * 登录判定
     * @param tbUser
     * @return
     */
    TbUser login(TbUser tbUser);

    /**
     * 用户注册
     * @param tbUser
     * @return
     */
    BaseResult register(TbUser tbUser);
}
