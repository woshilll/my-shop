package com.li.my.shop.commons.dto;

/**
 * 封装结果的工具类
 * @author 李洋
 * @date 2019-07-17 15:57
 */
public class BaseResult {
    private int status;
    private String message;
    public static final int STATUS_SUCCESS=200;
    public static final int STATUS_FAIL=500;
    public static BaseResult success(){
        return createBaseResult(STATUS_SUCCESS,"成功");
    }
    public static BaseResult success(String message){
        return createBaseResult(STATUS_SUCCESS,message);
    }


    public static BaseResult fail(){
        return createBaseResult(STATUS_FAIL,"失败");
    }
    public static BaseResult fail(String message){
        return createBaseResult(STATUS_FAIL,message);
    }
    public static BaseResult fail(int status,String message){
        return createBaseResult(status,message);
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    public static BaseResult createBaseResult(int status,String message){
        BaseResult baseResult=new BaseResult();
        baseResult.setStatus(status);
        baseResult.setMessage(message);
        return baseResult;
    }
}
