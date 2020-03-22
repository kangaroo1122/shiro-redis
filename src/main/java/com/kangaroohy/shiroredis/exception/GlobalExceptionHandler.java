package com.kangaroohy.shiroredis.exception;

import com.kangaroohy.shiroredis.domain.result.RestCode;
import com.kangaroohy.shiroredis.domain.result.RestResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

/**
 * 统一异常处理类
 * @author kangaroo
 * @date 2020/02/05
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 单独捕捉Shiro(UnauthorizedException)异常
     * 该异常为访问有权限管控的请求而该用户没有所需权限所抛出的异常
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(UnauthorizedException.class)
    public RestResult<String> unauthorizedExceptionHandler(UnauthorizedException e) {
        log.error("UnauthorizedException：" + e.getClass().getName(), e);
        return RestResult.error(RestCode.AUTHORIZATION_ERROR, "无权访问(Unauthorized):当前Subject没有此请求所需权限(" + e.getMessage() + ")");
    }

    /**
     * 需要登陆才能访问的URL
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(UnauthenticatedException.class)
    public RestResult<String> unauthenticatedExceptionHandler(UnauthenticatedException e) {
        log.error("UnauthenticatedException：" + e.getClass().getName(), e);
        return RestResult.error(RestCode.NEED_LOGIN_ERROR, "抱歉，请先登录");
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(AuthenticationException.class)
    public RestResult<String> authenticationExceptionHandler(AuthenticationException e) {
        log.error("AuthenticationException：" + e.getClass().getName(), e);
        return RestResult.error(RestCode.UNKNOWN_ERROR, e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(CustomException.class)
    public RestResult<String> customExceptionHandler(CustomException e) {
        log.error("CustomException：" + e.getClass().getName(), e);
        return RestResult.error(RestCode.PARAM_ERROR, e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public RestResult<String> missingServletRequestParameterException(MissingServletRequestParameterException e) {
        log.error("MissingServletRequestParameterException：" + e.getClass().getName(), e);
        return RestResult.error(RestCode.PARAM_ERROR, e.getMessage());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public RestResult<String> httpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        log.error("HttpRequestMethodNotSupportedException：" + e.getClass().getName(), e);
        return RestResult.error(RestCode.NOT_SUPPORT_ERROR, e.getMessage());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(RuntimeException.class)
    public RestResult<String> runtimeExceptionHandler(RuntimeException e) {
        log.error("RuntimeException：" + e.getClass().getName(), e);
        if (e instanceof MethodArgumentTypeMismatchException || e instanceof IllegalArgumentException) {
            return RestResult.error(RestCode.PARAM_ERROR, "请求参数格式不对，请检查");
        } else {
            return RestResult.error(RestCode.UNKNOWN_ERROR, e.getMessage());
        }
    }
}
