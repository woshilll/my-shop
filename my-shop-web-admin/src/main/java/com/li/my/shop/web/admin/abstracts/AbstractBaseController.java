package com.li.my.shop.web.admin.abstracts;

import com.li.my.shop.commons.dto.BaseResult;
import com.li.my.shop.commons.dto.PageInfo;
import com.li.my.shop.commons.persistence.BaseEntity;
import com.li.my.shop.commons.persistence.BaseService;
import com.li.my.shop.domain.TbUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 李洋
 * @date 2019-07-26 09:46
 */
public abstract class AbstractBaseController<T extends BaseEntity , S extends BaseService<T>> {
    @Autowired
    protected S service;

    /**
     * 跳转列表界面
     * @return
     */
    public abstract String list();

    /**
     * 跳转到表单界面
     * @return
     */
    public abstract String form();

    /**
     * 保存信息
     * @param entity
     * @param model
     * @param redirectAttributes
     * @return
     */
    public abstract String save(T entity, Model model, RedirectAttributes redirectAttributes);

    /**
     * 批量删除
     * @param ids
     * @return
     */
    public abstract BaseResult batchDelete(String ids);


    /**
     * 分页查询➕搜索
     * @param request
     * @param entity
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/page" , method = RequestMethod.GET)
    public  PageInfo<T> page(HttpServletRequest request , T entity){
        String strDraw = request.getParameter("draw");
        String strStart = request.getParameter("start");
        String strLength = request.getParameter("length");
        int draw = strDraw == null ? 0 : Integer.parseInt(strDraw);
        int start = strStart == null ? 0 : Integer.parseInt(strStart);
        int length = strLength == null ? 10 : Integer.parseInt(strLength);
        PageInfo<T> pageInfo = service.page(start, length, draw , entity);
        return pageInfo;
    }


    /**
     * 跳转到详情界面
     * @param entity
     * @return
     */
    public abstract String detail(T entity);
}
