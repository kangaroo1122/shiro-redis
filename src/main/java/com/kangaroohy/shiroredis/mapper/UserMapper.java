package com.kangaroohy.shiroredis.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kangaroohy.shiroredis.domain.entity.po.User;
import com.kangaroohy.shiroredis.domain.entity.vo.UserVO;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author kangaroo
 * @since 2020-03-06
 */
public interface UserMapper extends BaseMapper<User> {

    /**
     * 根据用户名查询用户信息，登录
     * @param username
     * @return
     */
    UserVO findUserInfoByUsername(String username);

    int insertUser(User u);
}
