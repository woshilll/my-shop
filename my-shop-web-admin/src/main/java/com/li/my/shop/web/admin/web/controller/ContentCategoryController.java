package com.li.my.shop.web.admin.web.controller;

import com.li.my.shop.commons.dto.BaseResult;
import com.li.my.shop.domain.TbContentCategory;
import com.li.my.shop.web.admin.abstracts.AbstractBaseTreeController;
import com.li.my.shop.web.admin.service.TbContentCategoryService;
import com.li.my.shop.web.admin.service.TbContentService;
import org.apache.commons.lang3.StringUtils;
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
public class ContentCategoryController extends AbstractBaseTreeController<TbContentCategory, TbContentCategoryService> {

    @Autowired
    private TbContentService tbContentService;

    /**
     * 跳转到内容分类列表界面
     *
     * @param model
     * @return
     */
    @Override
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        List<TbContentCategory> targetContentCategories = new ArrayList<>();
        List<TbContentCategory> sourceContentCategories = service.selectAll();
        sortList(sourceContentCategories, targetContentCategories, 0L);
        model.addAttribute("contentCategories", targetContentCategories);
        return "content_category_list";
    }


    /**
     * 在@RequestMapping前加载，用于在jsp中的<form:form/>绑定ModelAttribute属性
     *
     * @param id
     * @return
     */
    @ModelAttribute
    public TbContentCategory before(Long id) {
        TbContentCategory tbContentCategory;
        if (id == null) {
            tbContentCategory = new TbContentCategory();
        } else {
            tbContentCategory = service.getById(id);
        }
        return tbContentCategory;
    }


    /**
     * 跳转到单个表单界面，可用于新增、查看和修改
     *
     * @return
     */
    @Override
    @RequestMapping(value = "/form", method = RequestMethod.GET)
    public String form(TbContentCategory tbContentCategory) {
        return "content_category_form";
    }


    /**
     * 新增或修改用户信息
     *
     * @param tbContentCategory
     * @param model
     * @param redirectAttributes
     * @return
     */
    @Override
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(TbContentCategory tbContentCategory, Model model, RedirectAttributes redirectAttributes) {
        BaseResult baseResult = service.save(tbContentCategory);
        if (baseResult.getStatus() == BaseResult.STATUS_SUCCESS) {
            redirectAttributes.addFlashAttribute("baseResult", baseResult);
            return "redirect:/content/category/list";
        } else {
            model.addAttribute("baseResult", baseResult);
            return "content_category_form";
        }
    }


    /**
     * 分类树结构
     *
     * @param id
     * @return
     */
    @Override
    @ResponseBody
    @RequestMapping(value = "/tree/data", method = RequestMethod.POST)
    public List<TbContentCategory> selectByParentId(Long id) {
        return service.selectByParentId(id);
    }

    @Override
    @ResponseBody
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public BaseResult delete(String ids) {
        //强转
        Long id = Long.parseLong(ids);
        //获取当前分类信息
        TbContentCategory tbContentCategory = service.getById(id);
        //用于存放分类及其子分类id
        List<String> idList = new ArrayList<>();
        idList.add(id.toString());
        //遍历获得所有的子分类id
        getChildId(tbContentCategory, idList);
        //转化为String类型数组
        String[] idArray = idList.toArray(new String[idList.size()]);
        //先删除所有分类相关内容
        tbContentService.deleteByCategoryIds(idArray);
        //删除所有分类
        service.batchDelete(idArray);
        //获取父分类id
        Long parentId = tbContentCategory.getParent().getId();
        //得到父Id的所有孩子
        List<TbContentCategory> tbContentCategories = service.selectByParentId(parentId);
        //得到父分类详情信息
        TbContentCategory category = service.getById(parentId);
        //判断父分类是不是顶层分类，以及是否有孩子
        if (category.getParent().getId() != 0L && tbContentCategories.size() == 0){
            //不是顶级节点，且没有孩子，是叶子节点
            category.setIsParent(false);
            //更新
            service.updateById(category);
        }
        return BaseResult.success();
    }

    void getChildId(TbContentCategory tbContentCategory, List<String> ids) {
        if (tbContentCategory.getIsParent()) {
            List<TbContentCategory> tbContentCategories = service.selectByParentId(tbContentCategory.getId());
            for (TbContentCategory contentCategory : tbContentCategories) {
                ids.add(contentCategory.getId().toString());
                getChildId(contentCategory, ids);
                continue;
            }
        }
    }
}
