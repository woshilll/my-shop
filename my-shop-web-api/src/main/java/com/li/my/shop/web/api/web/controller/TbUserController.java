package com.li.my.shop.web.api.web.controller;

import com.li.my.shop.commons.dto.BaseResult;
import com.li.my.shop.domain.TbUser;
import com.li.my.shop.web.api.dto.TbUserDto;
import com.li.my.shop.web.api.service.TbUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 会员管理
 * @author 李洋
 * @date 2019-07-28 18:30
 */
@RestController
@RequestMapping(value = "${web.api.v1}/users")
public class TbUserController {
    @Autowired
    private TbUserService tbUserService;

    /**
     * 登录
     * @param tbUser
     * @return
     */
    @RequestMapping(value = "login" ,method = RequestMethod.POST)
    public BaseResult login(TbUser tbUser){
        TbUser user = tbUserService.login(tbUser);
        if (user != null){
            TbUserDto dto = new TbUserDto();
            BeanUtils.copyProperties(user , dto);
            return BaseResult.success("成功",dto);
        }
        return BaseResult.fail("用户名或密码错误");
    }


    /**
     * 用户注册
     * @param tbUser
     * @return
     */
    @RequestMapping(value = "register" , method = RequestMethod.POST)
    public BaseResult register(TbUser tbUser){
        return tbUserService.register(tbUser);
    }
}
