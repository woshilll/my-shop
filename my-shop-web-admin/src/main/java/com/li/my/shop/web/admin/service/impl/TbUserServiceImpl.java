package com.li.my.shop.web.admin.service.impl;

import com.li.my.shop.commons.dto.BaseResult;
import com.li.my.shop.commons.utils.RegexpUtils;
import com.li.my.shop.domain.PageInfo;
import com.li.my.shop.domain.TbUser;
import com.li.my.shop.web.admin.dao.TbUserDao;
import com.li.my.shop.web.admin.service.TbUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 李洋
 * @date 2019-07-16 12:30
 */
@Service
public class TbUserServiceImpl implements TbUserService {
    @Autowired
    private TbUserDao tbUserDao;
    @Override
    public List<TbUser> selectAll() {
        return tbUserDao.selectAll();
    }

    /**
     * 登录检查
     * @param email
     * @param password
     * @return
     */
    @Override
    public TbUser login(String email , String password) {
        TbUser tbUser = tbUserDao.getByEmail(email);
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
        BaseResult baseResult = checkTbUser(tbUser);
        if (baseResult.getStatus()==BaseResult.STATUS_SUCCESS){
            tbUser.setUpdated(new Date());
            //通过id判断是新增还是更新
            if (tbUser.getId() == null){
                //新增用户信息
                tbUser.setCreated(new Date());
                tbUser.setPassword(DigestUtils.md5DigestAsHex(tbUser.getPassword().getBytes()));
                tbUserDao.insert(tbUser);
                baseResult.setMessage("用户信息添加成功!");
            }else {
                //更新用户信息
                tbUserDao.updateById(tbUser);
                baseResult.setMessage("用户信息修改成功!");
            }

        }
        return baseResult;
    }

    /**
     * 通过id查找用户
     * @param id
     * @return
     */
    @Override
    public TbUser getById(Long id) {
        return tbUserDao.getById(id);
    }




    /**
     * 批量删除
     * @param ids
     */
    @Override
    public void batchDelete(String[] ids) {
        tbUserDao.batchDelete(ids);
    }

    /**
     * 查询总数
     * @return
     */
    @Override
    public int count(TbUser tbUser) {
        return tbUserDao.count(tbUser);
    }

    /**
     * 分页查询
     * @param start 起始位置
     * @param length 大小
     * @return
     */
    @Override
    public PageInfo<TbUser> page(int start, int length , int draw , TbUser tbUser) {
        PageInfo<TbUser> pageInfo=new PageInfo<>();
        pageInfo.setDraw(draw);
        pageInfo.setStart(start);
        pageInfo.setLength(length);
        Map<String , Object> pages = new HashMap<>(2);
        pages.put("start" , start);
        pages.put("length" , length);
        pages.put("tbUser",tbUser);
        List<TbUser> tbUsers = tbUserDao.page(pages);
        pageInfo.setData(tbUsers);
        int count=tbUserDao.count(tbUser);
        pageInfo.setRecordsFiltered(count);
        pageInfo.setRecordsTotal(count);
        return pageInfo;
    }

    /**
     * 检查TbUser是否满足条件
     * @param tbUser
     * @return
     */
    public BaseResult checkTbUser(TbUser tbUser){
        BaseResult baseResult = BaseResult.success();
        if (StringUtils.isBlank(tbUser.getEmail())){
            baseResult=BaseResult.fail("邮箱不能为空，请重新输入");
        }
        else if (!RegexpUtils.checkEmail(tbUser.getEmail())){
            baseResult=BaseResult.fail("邮箱不正确，请重新输入");
        }
        else if (StringUtils.isBlank(tbUser.getPassword())){
            baseResult=BaseResult.fail("密码不能为空，请重新输入");
        }
        else if (StringUtils.isBlank(tbUser.getUsername())){
            baseResult=BaseResult.fail("姓名不能为空，请重新输入");
        }
        else if (StringUtils.isBlank(tbUser.getPhone())){
            baseResult=BaseResult.fail("手机号不能为空，请重新输入");
        }
        else if (!RegexpUtils.checkPhone(tbUser.getPhone())){
            baseResult=BaseResult.fail("手机号不正确，请重新输入");
        }
        return baseResult;
    }
}
