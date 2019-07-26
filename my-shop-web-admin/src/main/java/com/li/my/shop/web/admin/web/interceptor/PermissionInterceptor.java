package com.li.my.shop.web.admin.web.interceptor;

import com.li.my.shop.commons.ConstantUtils;
import com.li.my.shop.domain.TbUser;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 权限拦截器
 * @author 李洋
 * @date 2019-07-15 17:42
 */
@Repository
public class PermissionInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        String login="login";
        if (modelAndView != null && modelAndView.getViewName() !=null && modelAndView.getViewName().endsWith(login)){
            TbUser user = (TbUser) httpServletRequest.getSession().getAttribute(ConstantUtils.SESSION_USER);
            if (user != null){
                //用户在登录后不能在进入登陆界面，重定向到主页面
                modelAndView.setViewName("main");
                httpServletResponse.sendRedirect("/main");
                return;
            }
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
