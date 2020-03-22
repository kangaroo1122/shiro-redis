package com.kangaroohy.shiroredis.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kangaroohy.shiroredis.domain.entity.po.User;
import com.kangaroohy.shiroredis.domain.entity.vo.UserVO;
import com.kangaroohy.shiroredis.mapper.UserMapper;
import com.kangaroohy.shiroredis.service.UserService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author kangaroo
 * @since 2020-03-06
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public UserVO findUserInfoByUsername(String username) {
        return getBaseMapper().findUserInfoByUsername(username);
    }

    @Override
    public int insertUser(User user) {
        return getBaseMapper().insertUser(user);
    }
}
