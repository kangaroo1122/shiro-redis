package com.kangaroohy.shiroredis.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kangaroohy.shiroredis.domain.entity.po.Role;
import com.kangaroohy.shiroredis.mapper.RoleMapper;
import com.kangaroohy.shiroredis.service.RoleService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author kangaroo
 * @since 2020-03-06
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

}
