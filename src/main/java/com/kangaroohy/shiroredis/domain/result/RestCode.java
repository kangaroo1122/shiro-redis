package com.kangaroohy.shiroredis.domain.result;

/**
 * @author kangaroo hy
 * @version 0.0.1
 * @desc TODO
 * @since 2020/3/13
 */
public enum RestCode {
    //运行时错误
    UNKNOWN_ERROR(-1, "Unknown Error! Contact the administrator if this problem continues."),
    //操作成功
    SUCCESS(200, "SUCCESS"),
    //请求参数错误、不合法
    PARAM_ERROR(400, "Illegal Parameter!"),
    //需要登录才能访问
    NEED_LOGIN_ERROR(401, "Please login and visit again!"),
    //没有访问权限，禁止访问
    AUTHORIZATION_ERROR(403, "You are forbidden to view it!"),
    //资源不存在
    NOTFOUND_ERROR(404, "Not Found"),
    //方法不支持
    NOT_SUPPORT_ERROR(500, "Not support this method");

    private Integer code;
    private String message;

    RestCode(){
    }

    RestCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
