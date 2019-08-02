package com.li.my.shop.web.admin.service.impl;

import com.li.my.shop.commons.dto.BaseResult;
import com.li.my.shop.commons.utils.RegexpUtils;
import com.li.my.shop.commons.validator.BeanValidator;
import com.li.my.shop.commons.dto.PageInfo;
import com.li.my.shop.domain.TbUser;
import com.li.my.shop.web.admin.abstracts.AbstractBaseServiceImpl;
import com.li.my.shop.web.admin.dao.TbUserDao;
import com.li.my.shop.web.admin.service.TbUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 李洋
 * @date 2019-07-16 12:30
 */
@Transactional(readOnly = true , rollbackFor = {})
@Service
public class TbUserServiceImpl extends AbstractBaseServiceImpl<TbUser , TbUserDao> implements TbUserService {


    /**
     * 登录检查
     * @param email
     * @param password
     * @return
     */
    @Override
    public TbUser login(String email , String password) {
        TbUser tbUser = dao.getByEmail(email);
        if (tbUser != null){
            //邮箱存在
            String md5Password= DigestUtils.md5DigestAsHex(password.getBytes());
            if (tbUser.getPassword().equals(md5Password)){
                //密码匹配
                return tbUser;
            }
        }
        return null;
    }

    /**
     * 更新或则新增用户
     * @param tbUser
     * @return
     */
    @Override
    public BaseResult save(TbUser tbUser) {
        String validator = BeanValidator.validator(tbUser);
        //验证不通过
        if (validator != null ){
            return BaseResult.fail(validator);
        }
        //验证通过
        else {
            tbUser.setUpdated(new Date());
            //通过id判断是新增还是更新
            if (tbUser.getId() == null){
                //新增用户信息
                tbUser.setCreated(new Date());
                tbUser.setPassword(DigestUtils.md5DigestAsHex(tbUser.getPassword().getBytes()));
                dao.insert(tbUser);
            }else {
                //更新用户信息
                dao.updateById(tbUser);
            }
            return BaseResult.success("保存用户信息成功!");
        }

    }


}
