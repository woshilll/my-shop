package com.li.my.shop.web.admin.service.impl;

import com.li.my.shop.commons.dto.BaseResult;
import com.li.my.shop.commons.validator.BeanValidator;
import com.li.my.shop.domain.TbContentCategory;
import com.li.my.shop.web.admin.abstracts.AbstractBaseTreeServiceImpl;
import com.li.my.shop.web.admin.dao.TbContentCategoryDao;
import com.li.my.shop.web.admin.service.TbContentCategoryService;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author 李洋
 * @date 2019-07-20 11:03
 */
@Service
public class TbContentCategoryServiceImpl extends AbstractBaseTreeServiceImpl<TbContentCategory , TbContentCategoryDao> implements TbContentCategoryService {
    @Override
    public BaseResult save(TbContentCategory entity) {
        String validator = BeanValidator.validator(entity);
        if (validator != null){
            return BaseResult.fail(validator);
        }else {
            TbContentCategory parent = entity.getParent();
            entity.setUpdated(new Date());
            if (parent==null || parent.getId() == null){
                //0代表根目录
                parent.setId(0L);
            }
            if (entity.getId() == null){
                entity.setCreated(new Date());
                entity.setIsParent(false);
                //判断当前新增节点有没有父节点
                if (parent.getId() != 0L){
                    TbContentCategory currentCategoryParent = getById(parent.getId());
                    if (currentCategoryParent != null){
                        //为父节点设置isParent为true
                        currentCategoryParent.setIsParent(true);
                        dao.updateById(currentCategoryParent);
                    }
                }else {
                    //父节点为0 表示根目录 根目录一定是父级目录
                    entity.setIsParent(true);
                }
                dao.insert(entity);
            }
            else {
                //更新分类
                dao.updateById(entity);
            }
            return BaseResult.success("保存分类信息成功");
        }
    }

}
