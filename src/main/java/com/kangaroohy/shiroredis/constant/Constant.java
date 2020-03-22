package com.kangaroohy.shiroredis.constant;

/**
 * @author kangaroo hy
 * @version 0.0.1
 * @desc TODO
 * @since 2020/3/7
 */
public class Constant {
    /**
     *  授权token
     */
    public static final String AUTHORIZATION = "Authorization";

    /**
     *  加密方法
     */
    public static final String HASH_NAME = "MD5";

    /**
     *  迭代次数
     */
    public static final Integer HASH_TIME = 1024;

    /**
     *  account
     */
    public static final String ACCOUNT = "username";

    /**
     * 权限cache过期时间-1分钟 单位 秒
     */
    public static final int CACHE_EXPIRE_TIME = 60;

    /**
     * session过期时间-15分钟  单位 秒
     */
    public static final long SESSION_EXPIRE_TIME = 15 * 60 * 1000;

    /**
     * 标记在线用户 过期时间-24小时  单位 秒
     */
    public static final Integer ONLINE_TOKEN_EXPIRE_TIME = 24 * 60 * 60;

    /**
     *  权限缓存
     *  redis-key-前缀-active:shiro:cache:
     */
    public static final String ACTIVE_SHIRO_CACHE = "active:shiro:cache:";

    /**
     *  shiro活跃session
     *  redis-key-前缀-active:shiro:session:
     */
    public static final String ACTIVE_SHIRO_SESSION = "active:shiro:session:";

    /**
     * 在线用户
     * redis-key-前缀-active:shiro:token:
     */
    public static final String ACTIVE_SHIRO_TOKEN = "active:shiro:token:";


    public static final int BYTE_ARRAY_OUTPUT_STREAM_SIZE = 128;

    /**
     * redis-OK
     */
    public static final String OK = "OK";
}
