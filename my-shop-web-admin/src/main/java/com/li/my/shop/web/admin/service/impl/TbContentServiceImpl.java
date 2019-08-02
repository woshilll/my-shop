package com.li.my.shop.web.admin.service.impl;

import com.li.my.shop.commons.dto.BaseResult;
import com.li.my.shop.commons.validator.BeanValidator;
import com.li.my.shop.commons.dto.PageInfo;
import com.li.my.shop.domain.TbContent;
import com.li.my.shop.web.admin.abstracts.AbstractBaseServiceImpl;
import com.li.my.shop.web.admin.dao.TbContentDao;
import com.li.my.shop.web.admin.service.TbContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 李洋
 * @date 2019-07-16 12:30
 */
@Service
public class TbContentServiceImpl extends AbstractBaseServiceImpl<TbContent , TbContentDao> implements TbContentService {


    /**
     * 更新或则新增用户
     * @param tbContent
     * @return
     */
    @Override
    public BaseResult save(TbContent tbContent) {
        String validator = BeanValidator.validator(tbContent);
        //验证不通过
        if (validator != null){
            return BaseResult.fail(validator);
        }
        //验证通过
        else{
            tbContent.setUpdated(new Date());
            //通过id判断是新增还是更新
            if (tbContent.getId() == null){
                //新增用户信息
                tbContent.setCreated(new Date());
                dao.insert(tbContent);
            }else {
                //更新用户信息
                dao.updateById(tbContent);
            }
            return BaseResult.success("保存内容成功");
        }

    }


    @Override
    public void deleteByCategoryIds(String[] ids) {
        dao.deleteByCategoryIds(ids);
    }
}
