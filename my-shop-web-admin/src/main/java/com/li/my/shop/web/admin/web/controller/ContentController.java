package com.li.my.shop.web.admin.web.controller;

import com.li.my.shop.commons.dto.BaseResult;
import com.li.my.shop.commons.dto.PageInfo;
import com.li.my.shop.domain.TbContent;
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

import javax.servlet.http.HttpServletRequest;

/**
 * @author 李洋
 * @date 2019-07-16 22:01
 */
@Controller
@RequestMapping("/content")
public class ContentController {
    @Autowired
    private TbContentService tbContentService;

    /**
     * 列表展示
     * @return
     */
    @RequestMapping(value = "/list" , method = RequestMethod.GET)
    public String list(){
        return "content_list";
    }

    /**
     * 在@RequestMapping前加载，用于在jsp中的<form:form/>绑定ModelAttribute属性
     * @param id
     * @return
     */
    @ModelAttribute
    public TbContent before(Long id){
        TbContent tbContent;
        if (id != null){
            tbContent=tbContentService.getById(id);
        }else {
            tbContent=new TbContent();
        }
        return tbContent;
    }

    /**
     * 跳转到单个表单界面，可用于新增、查看和修改
     * @return
     */
    @RequestMapping(value = "/form" , method = RequestMethod.GET)
    public String form(){
        return "content_form";
    }


    /**
     * 新增或修改用户信息
     * @param tbContent
     * @param model
     * @param redirectAttributes
     * @return
     */
    @RequestMapping(value = "/save" ,method = RequestMethod.POST)
    public String save(TbContent tbContent, Model model, RedirectAttributes redirectAttributes){
        BaseResult baseResult = tbContentService.save(tbContent);
        if (baseResult.getStatus() == BaseResult.STATUS_SUCCESS){
            redirectAttributes.addFlashAttribute("baseResult",baseResult);
            return "redirect:/content/list";
        }else {
            model.addAttribute("baseResult",baseResult);
            return "content_form";
        }
    }


    /**
     * 批量删除
     * @param ids
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/batchDelete" , method = RequestMethod.POST)
    public BaseResult batchDelete(String ids){
        BaseResult baseResult=BaseResult.fail("删除失败");
        if (StringUtils.isNotBlank(ids)){
            String[] idArray=ids.split(",");
            tbContentService.batchDelete(idArray);
            baseResult=BaseResult.success();
        }
        return baseResult;
    }

    @ResponseBody
    @RequestMapping(value = "/page" , method = RequestMethod.GET)
    public PageInfo<TbContent> page(HttpServletRequest request ,TbContent tbContent){
        String strDraw = request.getParameter("draw");
        String strStart = request.getParameter("start");
        String strLength = request.getParameter("length");
        int draw = strDraw == null ? 0 : Integer.parseInt(strDraw);
        int start = strStart == null ? 0 : Integer.parseInt(strStart);
        int length = strLength == null ? 10 : Integer.parseInt(strLength);
        PageInfo<TbContent> pageInfo = tbContentService.page(start, length, draw , tbContent);
        return pageInfo;
    }

    /**
     * 显示用户详情
     * @param tbContent
     * @return
     */
    @RequestMapping(value = "/detail" , method = RequestMethod.GET)
    public String detail(TbContent tbContent){
        return "content_detail";
    }
}
