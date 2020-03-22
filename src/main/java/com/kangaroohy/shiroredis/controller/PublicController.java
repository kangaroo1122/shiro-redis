package com.kangaroohy.shiroredis.controller;

import com.kangaroohy.shiroredis.utils.JedisUtil;
import com.kangaroohy.shiroredis.constant.Constant;
import com.kangaroohy.shiroredis.domain.entity.vo.UserVO;
import com.kangaroohy.shiroredis.domain.result.RestCode;
import com.kangaroohy.shiroredis.domain.result.RestResult;
import com.kangaroohy.shiroredis.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

/**
 * @author kangaroo hy
 * @version 0.0.1
 * @desc TODO
 * @since 2020/3/6
 */
@RestController
@RequestMapping("/pub")
@Slf4j
public class PublicController {

    UserService userService;

    public PublicController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/need_login")
    public RestResult<String> needLogin() {
        return RestResult.error(RestCode.NEED_LOGIN_ERROR);
    }

    @GetMapping("/unauthorized")
    public RestResult<String> unauthorized() {
        return RestResult.error(RestCode.AUTHORIZATION_ERROR);
    }

    @PostMapping("/login")
    public RestResult<UserVO> login(@RequestParam String username, @RequestParam String password, HttpServletResponse response) {
        Subject subject = SecurityUtils.getSubject();
        //踢出之前登录状态，同一账号 同一时间 只能在同一个地方登录
        kickOutBefore(username.trim());

        UserVO userInfo = userService.findUserInfoByUsername(username.trim());
        //用户名查询用户
        if (userInfo == null) {
            return RestResult.error("账号不存在");
        }
        try {
            UsernamePasswordToken userPwdToken = new UsernamePasswordToken(username.trim(), password.trim());
            subject.login(userPwdToken);
            String token = subject.getSession().getId().toString();
            UserVO info = userService.findUserInfoByUsername(username.trim());
            info.setPassword(null);
            JedisUtil.setJson(Constant.ACTIVE_SHIRO_TOKEN + username.trim(), token, Constant.ONLINE_TOKEN_EXPIRE_TIME);
            response.setHeader(Constant.AUTHORIZATION, token);
            response.setHeader("Access-Control-Expose-Headers", Constant.AUTHORIZATION);
            return RestResult.ok(info);
        } catch (Exception e) {
            e.printStackTrace();
            return RestResult.error("用户名或密码错误");
        }
    }

    /**
     * 根据userId踢出 同一用户 不同浏览器登录session
     * @param username 账号
     */
    private void kickOutBefore(String username) {
        String key = Constant.ACTIVE_SHIRO_TOKEN + username.trim();
        if (JedisUtil.exists(key)) {
            String value = JedisUtil.getJson(key);
            if (JedisUtil.exists(Constant.ACTIVE_SHIRO_SESSION + value)) {
                JedisUtil.delete(key, Constant.ACTIVE_SHIRO_SESSION + value);
            } else {
                JedisUtil.delete(key);
            }
        }
    }
}
