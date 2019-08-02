package com.li.my.shop.commons.utils;

import org.apache.http.HttpEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.Arrays;

/**
 * @author 李洋
 * @date 2019-07-28 16:22
 */
public class HttpClientUtils {
    public static final String GET = "get";
    public static final String POST = "post";
    public static final String REQUEST_HEADER_CONNECTION = "keep-alive";
    public static final String REQUEST_HEADER_USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/67.0.3396.87 Safari/537.36";
    /**
     * GET 请求
     *
     * @param url 请求地址
     * @return
     */
    public static String doGet(String url){
        return createRequest(url , GET , null);
    }
    /**
     * GET 请求
     *
     * @param url    请求地址
     * @param cookie cookie
     * @return
     */
    public static String doGet(String url, String cookie) {
        return createRequest(url, GET, cookie);
    }

    /**
     * POST 请求
     *
     * @param url    请求地址
     * @param params 请求参数（可选）
     * @return
     */
    public static String doPost(String url, BasicNameValuePair... params) {
        return createRequest(url, POST, null, params);
    }

    /**
     * POST 请求
     *
     * @param url    请求地址
     * @param cookie cookie
     * @param params 请求参数（可选）
     * @return
     */
    public static String doPost(String url, String cookie, BasicNameValuePair... params) {
        return createRequest(url, POST, cookie, params);
    }
    /**
     * 创建请求
     *
     * @param url           请求地址
     * @param requestMethod 请求方式 GET/POST
     * @param cookie        cookie
     * @param params        请求参数 仅限于 POST 请求用
     * @return
     */
    private static String createRequest(String url , String requestMethod , String cookie , BasicNameValuePair... params){
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String result = null;
        //
        HttpGet httpGet = null;
        HttpPost httpPost = null;
        CloseableHttpResponse httpResponse = null;

        try {
            if(GET.equals(requestMethod)){
                httpGet = new HttpGet(url);
                httpGet.setHeader("Connection" , REQUEST_HEADER_CONNECTION);
                httpGet.setHeader("cookie" , cookie);
                httpGet.setHeader("User-Agent" , REQUEST_HEADER_USER_AGENT);
                httpResponse = httpClient.execute(httpGet);
            }
            else if (POST.equals(requestMethod)){
                httpPost = new HttpPost(url);
                httpPost.setHeader("Connection" , REQUEST_HEADER_CONNECTION);
                httpPost.setHeader("cookie" , cookie);
                httpPost.setHeader("User-Agent" , REQUEST_HEADER_USER_AGENT);
                if (params != null && params.length > 0){
                    httpPost.setEntity(new UrlEncodedFormEntity(Arrays.asList(params) , "UTF-8"));
                }
                httpResponse = httpClient.execute(httpPost);
            }
            HttpEntity entity = httpResponse.getEntity();
            result = EntityUtils.toString(entity);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (httpClient != null){
                try {
                    httpClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }
}
