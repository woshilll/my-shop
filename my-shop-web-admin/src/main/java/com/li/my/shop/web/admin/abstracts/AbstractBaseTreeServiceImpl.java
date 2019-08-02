package com.li.my.shop.web.admin.abstracts;

import com.li.my.shop.commons.dto.BaseResult;
import com.li.my.shop.commons.persistence.BaseEntity;
import com.li.my.shop.commons.persistence.BaseTreeDao;
import com.li.my.shop.commons.persistence.BaseTreeService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author 李洋
 * @date 2019-07-25 18:27
 */
public abstract class AbstractBaseTreeServiceImpl<T extends BaseEntity , D extends BaseTreeDao<T>> implements BaseTreeService<T> {
    @Autowired
    protected D dao;

    /**
     * 查询全部
     * @return
     */
    @Override
    public List<T> selectAll() {
        return dao.selectAll();
    }

    /**
     * 通过id查询
     * @param id
     * @return
     */
    @Override
    public T getById(Long id) {
        return dao.getById(id);
    }

    /**
     * 通过父id查询
     * @param parentId
     * @return
     */
    @Override
    public List<T> selectByParentId(Long parentId) {
        if (parentId == null){
            parentId = 0L;
        }
        return dao.selectByParentId(parentId);
    }

    /**
     * 批量删除
     * @param ids
     */
    @Override
    public void batchDelete(String[] ids) {
        dao.batchDelete(ids);
    }

    @Override
    public void updateById(T entity) {
        dao.updateById(entity);
    }
}
