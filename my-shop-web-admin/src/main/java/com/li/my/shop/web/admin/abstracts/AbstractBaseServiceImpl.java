package com.li.my.shop.web.admin.abstracts;

import com.li.my.shop.commons.dto.BaseResult;
import com.li.my.shop.commons.dto.PageInfo;
import com.li.my.shop.commons.persistence.BaseDao;
import com.li.my.shop.commons.persistence.BaseEntity;
import com.li.my.shop.commons.persistence.BaseService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 李洋
 * @date 2019-07-25 18:54
 */
public abstract class AbstractBaseServiceImpl<T extends BaseEntity , D extends BaseDao<T>> implements BaseService<T> {
    @Autowired
    protected D dao;

    @Override
    public List<T> selectAll() {
        return dao.selectAll();
    }



    @Override
    public T getById(Long id) {
        return dao.getById(id);
    }

    @Override
    public void batchDelete(String[] ids) {
        dao.batchDelete(ids);
    }

    @Override
    public int count(T entity) {
        return dao.count(entity);
    }

    @Override
    public PageInfo<T> page(int start, int length, int draw, T entity) {
        PageInfo<T> pageInfo=new PageInfo<>();
        pageInfo.setDraw(draw);
        pageInfo.setStart(start);
        pageInfo.setLength(length);
        Map<String , Object> pages = new HashMap<>(16);
        pages.put("start" , start);
        pages.put("length" , length);
        pages.put("pageParams",entity);
        List<T> entities = dao.page(pages);
        pageInfo.setData(entities);
        int count=count(entity);
        pageInfo.setRecordsFiltered(count);
        pageInfo.setRecordsTotal(count);
        return pageInfo;
    }
}
