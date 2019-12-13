package net.likeyun.website.controller;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import net.likeyun.common.annotation.JwtIgnore;
import net.likeyun.common.entity.Audience;
import net.likeyun.common.entity.User;
import net.likeyun.common.enum_base.RoleEnum;
import net.likeyun.common.redis.RedisUtil;
import net.likeyun.common.response.Result;
import net.likeyun.common.service.LoginService;
import net.likeyun.common.service.UserService;
import net.likeyun.common.util.IPUtil;
import net.likeyun.common.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@Slf4j
public class LoginController {

    @Resource
    private Audience audience;

    @Resource
    private LoginService loginService;

    @Resource
    private UserService userService;

    @Resource
    private RedisUtil redisUtil;

    @PostMapping("/user/login")
    @JwtIgnore
    public Result adminLogin(HttpServletRequest request, HttpServletResponse response, @RequestBody User user) throws IOException {

        //登录
        User entity = loginService.login(user,request);

        // 登录成功，返回用户ID和角色
        String userId = entity.getId().toString();
        Integer roleValue = entity.getRole().getValue();
        String username = user.getUsername();

        // 创建token
        String token = JwtTokenUtil.createJWT(userId, username, roleValue, audience);
        log.info("### 登录成功, token={} ###", token);
        //把token存进redis中.时间单位为秒
        redisUtil.set(username, token, 60000);

        // 将token放在响应头
        response.setHeader(JwtTokenUtil.AUTH_HEADER_KEY, JwtTokenUtil.TOKEN_PREFIX + token);
        // 将token响应给客户端
        JSONObject result = new JSONObject();
        result.put("token", token);
        result.put("type", roleValue);
        return Result.SUCCESS(result);
    }

    @PostMapping("/register")
    @JwtIgnore
    public Result register(@RequestBody User user) {
        userService.register(user);
        return Result.SUCCESS();
    }

    @GetMapping("/admin/look")
    public Result userList() {
        log.info("### 查询所有用户列表 ###");
        return Result.SUCCESS();
    }


}
