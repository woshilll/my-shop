package com.li.my.shop.web.api.service.impl;

import com.li.my.shop.commons.dto.BaseResult;
import com.li.my.shop.commons.utils.EmailSendUtils;
import com.li.my.shop.domain.TbUser;
import com.li.my.shop.web.api.dao.TbUserDao;
import com.li.my.shop.web.api.service.TbUserService;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.Date;

/**
 * 会员管理
 * @author 李洋
 * @date 2019-07-28 18:25
 */
@Service
public class TbUserServiceImpl implements TbUserService {
    @Autowired
    private TbUserDao tbUserDao;
    @Autowired
    private EmailSendUtils emailSendUtils;
    /**
     * 登录判定
     * @param tbUser
     * @return
     */
    @Override
    public TbUser login(TbUser tbUser) {
        TbUser user = tbUserDao.login(tbUser);
        if (user != null){
            String password = DigestUtils.md5DigestAsHex(tbUser.getPassword().getBytes());
            if (password.equals(user.getPassword())){
                return user;
            }
        }
        return null;
    }

    /**
     * 用户注册
     * @param tbUser
     * @return
     */
    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public BaseResult register(TbUser tbUser) {
        int row = tbUserDao.checkRegister(tbUser);
        if (row > 0){
            //说明手机、邮箱或用户名存在
            return BaseResult.fail("该用户已注册！");
        }
        else {
            tbUser.setCreated(new Date());
            tbUser.setUpdated(new Date());
            //密码加密
            tbUser.setPassword(DigestUtils.md5DigestAsHex(tbUser.getPassword().getBytes()));
            row = tbUserDao.register(tbUser);
            if (row == 0){
                return BaseResult.fail("注册失败！请稍后再试");
            }
            else {
                try {
                    //像成功用户发送邮件
                    emailSendUtils.sendSimpleEmail("用户注册" , String.format("恭喜您 %s 注册成功",tbUser.getUsername()) , tbUser.getEmail());
                } catch (EmailException e) {
                    e.printStackTrace();
                }finally {
                    return BaseResult.success("注册成功");
                }

            }
        }
    }
}
