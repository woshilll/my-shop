package com.li.my.shop.web.admin.web.controller;

import com.li.my.shop.commons.dto.BaseResult;
import com.li.my.shop.domain.TbContent;
import com.li.my.shop.domain.TbContentCategory;
import com.li.my.shop.web.admin.service.TbContentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 李洋
 * @date 2019-07-20 11:05
 */
@Controller
@RequestMapping(value = "/content/category")
public class ContentCategoryController {
    @Autowired
    private TbContentCategoryService tbContentCategoryService;



    /**
     * 跳转到内容分类列表界面
     * @param model
     * @return
     */
    @RequestMapping(value = "/list" , method = RequestMethod.GET)
    public String list(Model model){
        List<TbContentCategory> targetContentCategories = new ArrayList<>();
        List<TbContentCategory> sourceContentCategories = tbContentCategoryService.selectAll();
        sortContentCategory(sourceContentCategories , targetContentCategories , 0L);
        model.addAttribute("contentCategories" , targetContentCategories);
        return "content_category_list";
    }



    /**
     * 在@RequestMapping前加载，用于在jsp中的<form:form/>绑定ModelAttribute属性
     * @param id
     * @return
     */
    @ModelAttribute
    public TbContentCategory before(Long id){
        TbContentCategory tbContentCategory;
        if (id == null){
            tbContentCategory=new TbContentCategory();
        }else {
            tbContentCategory=tbContentCategoryService.getById(id);
        }
        return tbContentCategory;
    }


    /**
     * 跳转到单个表单界面，可用于新增、查看和修改
     * @return
     */
    @RequestMapping(value = "/form" , method = RequestMethod.GET)
    public String form(TbContentCategory tbContentCategory){
        return "content_category_form";
    }




    /**
     * 新增或修改用户信息
     * @param tbContentCategory
     * @param model
     * @param redirectAttributes
     * @return
     */
    @RequestMapping(value = "/save" ,method = RequestMethod.POST)
    public String save(TbContentCategory tbContentCategory, Model model, RedirectAttributes redirectAttributes){
        BaseResult baseResult = tbContentCategoryService.save(tbContentCategory);
        if (baseResult.getStatus() == BaseResult.STATUS_SUCCESS){
            redirectAttributes.addFlashAttribute("baseResult",baseResult);
            return "redirect:/content/category/list";
        }else {
            model.addAttribute("baseResult",baseResult);
            return "content_category_form";
        }
    }



    /**
     * 分类树结构
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/tree/data" , method = RequestMethod.POST)
    public List<TbContentCategory> selectByParentId(Long id){
        return tbContentCategoryService.selectByParentId(id);
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
