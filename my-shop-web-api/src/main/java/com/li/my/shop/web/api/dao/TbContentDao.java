package com.li.my.shop.web.api.dao;

import com.li.my.shop.domain.TbContent;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 李洋
 * @date 2019-07-27 15:13
 */
@Repository
public interface TbContentDao {
    /**
     * 通过category id查找
     * @param tbContent
     * @return
     */
    List<TbContent> findByCategoryId(TbContent tbContent);
}
