package com.li.my.shop.web.api.web;

import com.li.my.shop.domain.TbContent;
import com.li.my.shop.web.api.service.TbContentService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * @author 李洋
 * @date 2019-07-27 16:12
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-mvc.xml" ,"classpath:spring-context.xml" , "classpath:spring-context-druid.xml" , "classpath:spring-context-mybatis.xml"})
public class Test {
    @Autowired
    private TbContentService tbContentService;

    @org.junit.Test
    public void test(){
        List<TbContent> tbContents = tbContentService.findByCategoryId(89L);
        for (TbContent tbContent : tbContents) {
            System.out.println(tbContent.getTitle());
        }
    }
}
