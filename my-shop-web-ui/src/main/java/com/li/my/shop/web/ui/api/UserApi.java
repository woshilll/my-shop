package com.li.my.shop.web.ui.api;

import com.li.my.shop.commons.dto.BaseResult;
import com.li.my.shop.commons.utils.HttpClientUtils;
import com.li.my.shop.commons.utils.MapperUtils;
import com.li.my.shop.web.ui.dto.TbUser;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 李洋
 * @date 2019-07-29 10:34
 */
public class UserApi {
    /**
     * 连接API的登录接口
     * @param tbUser
     * @return
     * @throws Exception
     */
    public static TbUser login(TbUser tbUser) throws Exception {
        String json = post(Api.API_LOGIN, tbUser);
        TbUser user = MapperUtils.json2pojoByTree(json, "data", TbUser.class);
        return user;
    }

    /**
     * 连接API的注册接口
     * @param tbUser
     * @return
     * @throws Exception
     */
    public static BaseResult register(TbUser tbUser) throws Exception {
        String json = post(Api.API_REGISTER, tbUser);
        Integer status = MapperUtils.json2pojoByTree(json, "status", Integer.class);
        String message = MapperUtils.json2pojoByTree(json, "message", String.class);
        //注册成功返回true
        if (status == BaseResult.STATUS_SUCCESS){
            return BaseResult.success(message);
        }
        return BaseResult.fail(message);
    }
    private static String post(String url , TbUser tbUser){
        List<BasicNameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("username" , tbUser.getUsername()));
        params.add(new BasicNameValuePair("password" , tbUser.getPassword()));
        params.add(new BasicNameValuePair("email" , tbUser.getEmail()));
        params.add(new BasicNameValuePair("phone" , tbUser.getPhone()));
        String json = HttpClientUtils.doPost(url, params.toArray(new BasicNameValuePair[params.size()]));
        return json;
    }
}
