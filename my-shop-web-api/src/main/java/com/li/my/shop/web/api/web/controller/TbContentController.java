package com.li.my.shop.web.api.web.controller;

import com.li.my.shop.commons.dto.BaseResult;
import com.li.my.shop.domain.TbContent;
import com.li.my.shop.web.api.dto.TbContentDto;
import com.li.my.shop.web.api.service.TbContentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 内容管理
 * @author 李洋
 * @date 2019-07-27 15:24
 */
@RestController
@RequestMapping(value = "${web.api.v1}/contents/")
public class TbContentController {
    @Autowired
    private TbContentService tbContentService;


    /**
     * ppt展示
     * @param categoryId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "ppt/{category_id}", method = RequestMethod.GET)
    public BaseResult findByCategoryId(@PathVariable(value = "category_id") Long categoryId) {
        List<TbContent> tbContents = tbContentService.findByCategoryId(categoryId);
        List<TbContentDto> tbContentDtos = null;
        if (tbContents != null && tbContents.size() > 0){
            tbContentDtos = new ArrayList<>();
            for (TbContent tbContent : tbContents) {
                TbContentDto tbContentDto = new TbContentDto();
                BeanUtils.copyProperties(tbContent , tbContentDto);
                tbContentDtos.add(tbContentDto);
            }
        }
        return BaseResult.success("成功" , tbContentDtos);
    }
}
