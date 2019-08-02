package com.li.my.shop.web.admin.abstracts;

import com.li.my.shop.commons.dto.BaseResult;
import com.li.my.shop.commons.persistence.BaseEntity;
import com.li.my.shop.commons.persistence.BaseTreeEntity;
import com.li.my.shop.commons.persistence.BaseTreeService;
import com.li.my.shop.domain.TbContentCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * @author 李洋
 * @date 2019-07-26 09:58
 */
public abstract class AbstractBaseTreeController<T extends BaseTreeEntity, S extends BaseTreeService<T>> {
    @Autowired
    protected S service;


    /**
     * 跳转到列表界面
     * @param model
     * @return
     */
    public abstract String list(Model model);


    /**
     * 跳转到表单界面
     * @param entity
     * @return
     */
    public abstract String form(T entity);

    /**
     * 保存信息
     * @param entity
     * @param model
     * @param redirectAttributes
     * @return
     */
    public abstract String save(T entity, Model model, RedirectAttributes redirectAttributes);

    /**
     * 分类树
     * @param id
     * @return
     */
    public abstract List<T> selectByParentId(Long id);

    /**
     * 通过id删除分类及其子分类，以及所有分类相关的内容
     * @param ids
     * @return
     */
    public abstract BaseResult delete(String ids);

    protected void sortList(List<T> sourceEntities , List<T> targetEntities , Long parentId){
        for (T sourceEntity : sourceEntities) {
            if (sourceEntity.getParent().getId().equals(parentId)){
                targetEntities.add(sourceEntity);
                if (sourceEntity.getIsParent()){
                    for (T currentEntity : sourceEntities) {
                        if (currentEntity.getParent().getId().equals(sourceEntity.getId())){
                            sortList(sourceEntities , targetEntities , sourceEntity.getId());
                            break;
                        }

                    }
                }
            }
        }
    }
}
