package com.li.my.shop.web.ui.api;

/**
 * @author 李洋
 * @date 2019-07-28 16:41
 */
public class Api {
    /**
     *主机地址
     */
    public static final String HOST = "http://localhost:8081/api/v1";

    /**
     * 内容查询接口 - 幻灯片
     */
    public static final String API_CONTENTS_PPT = HOST + "/contents/ppt/";

    /**
     * 登录接口
     */
    public static final String API_LOGIN = HOST + "/users/login";

    /**
     * 注册接口
     */
    public static final String API_REGISTER = HOST + "/users/register";
}
