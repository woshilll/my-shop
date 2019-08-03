package com.li.my.shop.web.admin.dao;

import com.li.my.shop.commons.persistence.BaseDao;
import com.li.my.shop.domain.TbUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


/**
 * @author 李洋
 * @date 2019-07-16 12:27
 */
@Repository
public interface TbUserDao extends BaseDao<TbUser> {
    /**
     * 通过邮箱查找用户
     * @param email
     * @return
     */
    TbUser getByEmail(String email);

    /**
     * 通过邮箱修改密码
     * @param email
     * @param password
     */
    void changePwd(@Param("email") String email ,@Param("password") String password);
}
