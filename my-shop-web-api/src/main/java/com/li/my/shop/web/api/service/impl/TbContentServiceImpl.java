package com.li.my.shop.web.api.service.impl;

import com.li.my.shop.domain.TbContent;
import com.li.my.shop.domain.TbContentCategory;
import com.li.my.shop.web.api.dao.TbContentDao;
import com.li.my.shop.web.api.service.TbContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @author 李洋
 * @date 2019-07-27 15:21
 */
@Service
public class TbContentServiceImpl implements TbContentService {
    @Autowired
    private TbContentDao tbContentDao;
    @Override
    public List<TbContent> findByCategoryId(Long categoryId) {
        TbContent tbContent = new TbContent();
        TbContentCategory tbContentCategory = new TbContentCategory();
        tbContentCategory.setId(categoryId);
        tbContent.setTbContentCategory(tbContentCategory);
        return tbContentDao.findByCategoryId(tbContent);
    }
}
