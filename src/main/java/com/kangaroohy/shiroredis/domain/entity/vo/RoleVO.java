package com.kangaroohy.shiroredis.domain.entity.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.kangaroohy.shiroredis.domain.entity.po.Permission;
import com.kangaroohy.shiroredis.domain.entity.po.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author kangaroo
 * @since 2019-12-09
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoleVO implements Serializable {

    private static final long serialVersionUID = 2052039837879085137L;

    /**
     * 角色基本信息
     */
    @JSONField(ordinal = 1)
    private Role role;

    /**
     * 权限集合
     */
    @JSONField(ordinal = 2)
    private List<Permission> permissionList;

}
