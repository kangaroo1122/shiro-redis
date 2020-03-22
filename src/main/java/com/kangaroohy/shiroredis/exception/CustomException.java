package com.kangaroohy.shiroredis.exception;

/**
 * @author kangaroo hy
 * @version 0.0.1
 * @desc 自定义异常(CustomException)
 * @since 2020/3/7
 */
public class CustomException extends RuntimeException {

    public CustomException(String msg){
        super(msg);
    }

    public CustomException() {
        super();
    }
}
