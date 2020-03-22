package com.kangaroohy.shiroredis.config.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author kangaroo hy
 * @version 0.0.1
 * @desc TODO
 * @since 2020/3/13
 */
@Configuration
@EnableCaching
@Slf4j
public class JedisConfig extends CachingConfigurerSupport {
    @Value("${spring.redis.database}")
    private Integer database;

    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private Integer port;

    @Value("${spring.redis.password}")
    private String password;

    @Value("${spring.redis.timeout}")
    private Integer timeout;

    @Bean
    public JedisPool redisPoolFactory(){
        try {
            JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
            // 密码为空设置为null
            if (StringUtils.isEmpty(password)) {
                password = null;
            }
            JedisPool jedisPool = new JedisPool(jedisPoolConfig, host, port, timeout, password, database);
            log.info("初始化Redis连接池JedisPool成功!地址: {}:{}", host, port);
            return jedisPool;
        } catch (Exception e) {
            log.error("初始化Redis连接池JedisPool异常:{}", e.getMessage());
        }
        return null;
    }
}
