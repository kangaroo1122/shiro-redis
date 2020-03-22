package com.kangaroohy.shiroredis.domain.result;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;

/**
 * @author kangaroo hy
 * @version 0.0.1
 * @desc TODO
 * @since 2020/3/13
 */
@Data
public class RestResult<T> implements Serializable {

    private static final long serialVersionUID = 7711799662216684129L;

    @JSONField(ordinal = 1)
    public int code;

    @JSONField(ordinal = 2)
    private String msg;

    @JSONField(ordinal = 3)
    private T data;

    public RestResult() {
    }

    public RestResult(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public RestResult(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static <T> RestResult<T> ok() {
        return new RestResult<>(RestCode.SUCCESS.getCode(), RestCode.SUCCESS.getMessage());
    }

    public static <T> RestResult<T> ok(String msg) {
        return new RestResult<>(RestCode.SUCCESS.getCode(), msg);
    }

    public static <T> RestResult<T> ok(T data) {
        return new RestResult<>(RestCode.SUCCESS.getCode(), RestCode.SUCCESS.getMessage(), data);
    }

    public static <T> RestResult<T> error(String msg) {
        return new RestResult<>(RestCode.UNKNOWN_ERROR.getCode(), msg);
    }

    public static <T> RestResult<T> error(RestCode restCode) {
        return new RestResult<>(restCode.getCode(), restCode.getMessage());
    }

    public static <T> RestResult<T> error(RestCode restCode, String msg) {
        return new RestResult<>(restCode.getCode(), msg);
    }

    public static <T> RestResult<T> error(RestCode restCode, String msg, T data) {
        return new RestResult<>(restCode.getCode(), msg, data);
    }
}
