package com.li.my.shop.web.admin.web.controller;

import com.li.my.shop.commons.dto.BaseResult;
import com.li.my.shop.commons.dto.PageInfo;
import com.li.my.shop.domain.TbUser;
import com.li.my.shop.web.admin.abstracts.AbstractBaseController;
import com.li.my.shop.web.admin.service.TbUserService;
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
@RequestMapping("/user")
public class UserController extends AbstractBaseController<TbUser , TbUserService> {

    /**
     * 用户列表展示
     * @return
     */
    @Override
    @RequestMapping(value = "/list" , method = RequestMethod.GET)
    public String list(){
        return "user_list";
    }

    /**
     * 在@RequestMapping前加载，用于在jsp中的<form:form/>绑定ModelAttribute属性
     * @param id
     * @return
     */
    @ModelAttribute
    public TbUser before(Long id){
        TbUser tbUser;
        if (id != null){
            tbUser=service.getById(id);
        }else {
            tbUser=new TbUser();
        }
        return tbUser;
    }

    /**
     * 跳转到单个用户表单界面，可用于新增、查看和修改
     * @return
     */
    @Override
    @RequestMapping(value = "/form" , method = RequestMethod.GET)
    public String form(){
        return "user_form";
    }


    /**
     * 新增或修改用户信息
     * @param tbUser
     * @param model
     * @param redirectAttributes
     * @return
     */
    @Override
    @RequestMapping(value = "/save" ,method = RequestMethod.POST)
    public String save(TbUser tbUser, Model model, RedirectAttributes redirectAttributes){
        BaseResult baseResult = service.save(tbUser);
        if (baseResult.getStatus() == BaseResult.STATUS_SUCCESS){
            redirectAttributes.addFlashAttribute("baseResult",baseResult);
            return "redirect:/user/list";
        }else {
            model.addAttribute("baseResult",baseResult);
            return "user_form";
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
     * @param tbUser
     * @return
     */
    @Override
    @RequestMapping(value = "/detail" , method = RequestMethod.GET)
    public String detail(TbUser tbUser){
        return "user_detail";
    }
}
