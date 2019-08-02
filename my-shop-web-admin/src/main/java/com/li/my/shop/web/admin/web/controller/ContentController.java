package com.li.my.shop.web.admin.web.controller;

import com.li.my.shop.commons.dto.BaseResult;
import com.li.my.shop.commons.dto.PageInfo;
import com.li.my.shop.domain.TbContent;
import com.li.my.shop.web.admin.abstracts.AbstractBaseController;
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
public class ContentController extends AbstractBaseController<TbContent , TbContentService> {

    /**
     * 列表展示
     * @return
     */
    @Override
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
            tbContent=service.getById(id);
        }else {
            tbContent=new TbContent();
        }
        return tbContent;
    }

    /**
     * 跳转到单个表单界面，可用于新增、查看和修改
     * @return
     */
    @Override
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
    @Override
    @RequestMapping(value = "/save" ,method = RequestMethod.POST)
    public String save(TbContent tbContent, Model model, RedirectAttributes redirectAttributes){
        BaseResult baseResult = service.save(tbContent);
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
    @Override
    @ResponseBody
    @RequestMapping(value = "/batchDelete" , method = RequestMethod.POST)
    public BaseResult batchDelete(String ids){
        BaseResult baseResult=BaseResult.fail("删除失败");
        if (StringUtils.isNotBlank(ids)){
            String[] idArray=ids.split(",");
            service.batchDelete(idArray);
            baseResult=BaseResult.success();
        }
        return baseResult;
    }
    /**
     * 显示用户详情
     * @param tbContent
     * @return
     */
    @Override
    @RequestMapping(value = "/detail" , method = RequestMethod.GET)
    public String detail(TbContent tbContent){
        return "content_detail";
    }
}
