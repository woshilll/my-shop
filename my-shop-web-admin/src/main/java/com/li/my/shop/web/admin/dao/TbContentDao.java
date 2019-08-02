package com.li.my.shop.web.admin.dao;

import com.li.my.shop.commons.persistence.BaseDao;
import com.li.my.shop.domain.TbContent;
import org.springframework.stereotype.Repository;
/**
 * @author 李洋
 * @date 2019-07-16 12:27
 */
@Repository
public interface TbContentDao extends BaseDao<TbContent> {
    /**
     * 通过分类id删除所属内容
     * @param ids
     */
    void deleteByCategoryIds(String[] ids);
}
