package com.kangaroohy.shiroredis.config.shiro;

import com.kangaroohy.shiroredis.domain.entity.po.Permission;
import com.kangaroohy.shiroredis.domain.entity.vo.RoleVO;
import com.kangaroohy.shiroredis.domain.entity.vo.UserVO;
import com.kangaroohy.shiroredis.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 自定义Shiro Realm
 * @author kangaroo
 * @date 2019/12/9
 */
@Slf4j
public class CustomRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;

    /**
     * 授权时调用（权限校验）
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //获得当前对象
        UserVO user = (UserVO) principalCollection.getPrimaryPrincipal();

        log.info("用户 {} 调用了 doGetAuthorizationInfo 进行授权", user.getUsername());

        UserVO info = userService.findUserInfoByUsername(user.getUsername());

        Set<String> stringRolesList = new HashSet<>();
        Set<String> stringPermissionsList = new HashSet<>();

        List<RoleVO> roleList = info.getRoleList();
        for (RoleVO role : roleList) {
            stringRolesList.add(role.getRole().getName());
            List<Permission> permissionList = role.getPermissionList();
            for (Permission permission : permissionList){
                if (permission != null){
                    stringPermissionsList.add(permission.getName());
                }
            }
        }

        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.addRoles(stringRolesList);
        simpleAuthorizationInfo.addStringPermissions(stringPermissionsList);
        return simpleAuthorizationInfo;
    }

    /**
     * 登录时调用
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //从token中获得用户信息，这个token是用户的输入信息
        String username = (String) authenticationToken.getPrincipal();
        UserVO info = userService.findUserInfoByUsername(username);

        //取密码
        String password = info.getPassword();
        if (password == null || "".equals(password)) {
            return null;
        }
        //设置盐值，使用账号作为盐值
        ByteSource salt = ByteSource.Util.bytes(username);
        //参数一传入对象，便于全局获取当前对象信息
        return new SimpleAuthenticationInfo(info, password, salt, getName());
    }
}
