package com.kangaroohy.shiroredis.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kangaroohy.shiroredis.constant.Constant;
import com.kangaroohy.shiroredis.domain.entity.po.User;
import com.kangaroohy.shiroredis.domain.entity.vo.UserVO;
import com.kangaroohy.shiroredis.domain.result.RestResult;
import com.kangaroohy.shiroredis.exception.CustomException;
import com.kangaroohy.shiroredis.service.UserService;
import com.kangaroohy.shiroredis.utils.JedisUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author kangaroo
 * @since 2020-03-06
 */
@RestController
@RequestMapping("/user")
public class UserController {

    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/list")
    @RequiresPermissions("query")
    public RestResult<List<User>> findAllUser() {
        QueryWrapper<User> query = new QueryWrapper<>();
        query.select(User.class, column -> !"password".equals(column.getColumn()));
        return RestResult.ok(userService.list(query));
    }

    @PostMapping("/insert")
    public RestResult<String> insertUser(@RequestBody User user) {
        System.out.println(user);
        userService.insertUser(user);
        return RestResult.ok();
    }

    /**
     * 查看在线用户
     *
     * @return 在线用户列表
     */
    @GetMapping("/online")
    @RequiresPermissions("query")
    public RestResult<List<User>> findAllOnlineUser() {
        List<User> userList = new ArrayList<>();
        // 查询所有Redis键
        Set<String> keys = JedisUtil.keysS(Constant.ACTIVE_SHIRO_TOKEN + "*");
        for (String key : keys) {
            if (JedisUtil.exists(key)) {
                String value = JedisUtil.getJson(key);
                if (JedisUtil.exists(Constant.ACTIVE_SHIRO_SESSION + value)) {
                    // 根据:分割key，获取最后一个字符串(帐号)
                    String[] strArray = key.split(":");
                    QueryWrapper<User> query = new QueryWrapper<>();
                    query.select(User.class, info -> !"password".equals(info.getColumn()))
                            .eq("username", strArray[strArray.length - 1]);
                    User user = userService.getOne(query);
                    userList.add(user);
                }
            }
        }
        return RestResult.ok(userList);
    }

    /**
     * 剔除在线用户
     *
     * @param id 用户id
     * @return 是否成功
     */
    @PostMapping("/online/delete/{id}")
    @RequiresPermissions({"delete"})
    public RestResult<String> deleteOnlineUser(@PathVariable("id") Integer id) {
        if (id.equals(getLoginUser().getId())) {
            return RestResult.error("踢出失败，不能踢出自己");
        }
        User user = userService.getById(id);
        String key = Constant.ACTIVE_SHIRO_TOKEN + user.getUsername();
        if (JedisUtil.exists(key)) {
            String value = JedisUtil.getJson(key);
            if (JedisUtil.exists(Constant.ACTIVE_SHIRO_SESSION + value)) {
                JedisUtil.delete(key, Constant.ACTIVE_SHIRO_SESSION + value);
            } else {
                JedisUtil.delete(key);
            }
            return RestResult.ok("踢出登录状态成功");
        }
        throw new CustomException("踢出失败，Account不存在(Deletion Failed. Account does not exist.)");
    }

    private UserVO getLoginUser() {
        return (UserVO) SecurityUtils.getSubject().getPrincipal();
    }
}
