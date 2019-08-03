package com.li.my.shop.web.admin.service;

import com.li.my.shop.commons.persistence.BaseService;
import com.li.my.shop.domain.TbUser;
import org.apache.ibatis.annotations.Param;

/**
 * @author 李洋
 * @date 2019-07-16 12:28
 */
public interface TbUserService extends BaseService<TbUser> {
    /**
     * 登录功能
     * @param email
     * @param password
     * @return
     */
    TbUser login(String email , String password);

    /**
     * 通过邮箱修改密码
     * @param email
     * @param password
     */
    void changePwd(String email , String password);
}
