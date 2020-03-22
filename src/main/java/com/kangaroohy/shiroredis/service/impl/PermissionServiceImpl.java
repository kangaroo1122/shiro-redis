package com.kangaroohy.shiroredis.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kangaroohy.shiroredis.domain.entity.po.Permission;
import com.kangaroohy.shiroredis.mapper.PermissionMapper;
import com.kangaroohy.shiroredis.service.PermissionService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 权限表 服务实现类
 * </p>
 *
 * @author kangaroo
 * @since 2020-03-06
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {

}
