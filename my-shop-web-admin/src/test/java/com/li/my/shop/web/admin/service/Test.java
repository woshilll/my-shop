package com.li.my.shop.web.admin.service;

import com.li.my.shop.domain.TbContentCategory;
import com.li.my.shop.domain.TbUser;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 李洋
 * @date 2019-07-16 12:34
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-mvc.xml" ,"classpath:spring-context.xml" , "classpath:spring-context-druid.xml" , "classpath:spring-context-mybatis.xml"})
public class Test {
    @Autowired
    private TbContentCategoryService tbContentCategoryService;

    @org.junit.Test
    public void list(){
        List<TbContentCategory> targetContentCategories = new ArrayList<>();
        List<TbContentCategory> sourceContentCategories = tbContentCategoryService.selectAll();
        sortContentCategory(sourceContentCategories , targetContentCategories , 0L);
    }
    private void sortContentCategory(List<TbContentCategory> sourceContentCategories , List<TbContentCategory> targetContentCategories , Long parentId){
        for (TbContentCategory sourceContentCategory : sourceContentCategories) {
            if (sourceContentCategory.getParent().getId().equals(parentId)){
                targetContentCategories.add(sourceContentCategory);
                if (sourceContentCategory.getIsParent()){
                    for (TbContentCategory contentCategory : sourceContentCategories) {
                        if (contentCategory.getParent().getId().equals(sourceContentCategory.getId())){
                            sortContentCategory(sourceContentCategories , targetContentCategories , sourceContentCategory.getId());
                            break;
                        }

                    }
                }
            }
        }
    }
}
