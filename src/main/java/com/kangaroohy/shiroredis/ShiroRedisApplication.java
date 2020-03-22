package com.kangaroohy.shiroredis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.kangaroohy.shiroredis.mapper")
public class ShiroRedisApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShiroRedisApplication.class, args);
    }

}
