package com.kangaroohy.shiroredis.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kangaroohy.shiroredis.domain.entity.po.User;
import com.kangaroohy.shiroredis.domain.entity.vo.UserVO;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author kangaroo
 * @since 2020-03-06
 */
public interface UserService extends IService<User> {
    /**
     * 根据用户名查询用户信息，登录
     * @param username
     * @return
     */
    UserVO findUserInfoByUsername(String username);

    int insertUser(User user);
}
