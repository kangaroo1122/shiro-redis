package com.kangaroohy.shiroredis.config.shiro;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionIdGenerator;

import java.io.Serializable;
import java.util.UUID;

/**
 * @author kangaroo hy
 * @version 0.0.1
 * @desc 自定义sessionId生成方法
 * @since 2020/3/14
 */
public class CustomSessionId implements SessionIdGenerator {
    @Override
    public Serializable generateId(Session session) {
        return "kangaroohy" + UUID.randomUUID().toString().replace("-", "");
    }
}
