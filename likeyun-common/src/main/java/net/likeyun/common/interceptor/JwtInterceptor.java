package net.likeyun.common.interceptor;

import lombok.extern.slf4j.Slf4j;
import net.likeyun.common.annotation.JwtIgnore;
import net.likeyun.common.entity.Audience;
import net.likeyun.common.enum_base.RoleEnum;
import net.likeyun.common.exception.CustomException;
import net.likeyun.common.redis.RedisUtil;
import net.likeyun.common.response.ResultCode;
import net.likeyun.common.service.UserService;
import net.likeyun.common.util.JwtTokenUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.RedisConnectionFailureException;
import org.springframework.http.HttpMethod;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Set;

/**
 * @Description: token验证拦截器
 * @Author: lfy
 * @Date: 2019/11/28 14:01
 */
@Slf4j
public class JwtInterceptor extends HandlerInterceptorAdapter {

    @Resource
    private Audience audience;

    @Resource
    private UserService userService;

    @Resource
    private RedisUtil redisUtil;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

        // 忽略带JwtIgnore注解的请求, 不做后续token认证校验
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            JwtIgnore jwtIgnore = handlerMethod.getMethodAnnotation(JwtIgnore.class);
            if (jwtIgnore != null) {
                return true;
            }
        }
        if ("OPTIONS".equals(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
            return true;
        }

        // 获取请求头信息authorization信息
        final String authHeader = request.getHeader(JwtTokenUtil.AUTH_HEADER_KEY);
        log.info("## authHeader= {}", authHeader);

        if (StringUtils.isBlank(authHeader) || !authHeader.startsWith(JwtTokenUtil.TOKEN_PREFIX)) {
            log.info("### 用户未登录，请先登录 ###");
            throw new CustomException(ResultCode.USER_NOT_LOGGED_IN);
        }

        // 获取token
        final String token = authHeader.substring(7);

        if (audience == null) {
            BeanFactory factory = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getServletContext());
            audience = (Audience) factory.getBean("audience");
        }

        // 验证token是否有效--无效已做异常抛出，由全局异常处理后返回对应信息
        JwtTokenUtil.parseJWT(token, audience.getBase64Secret());
        //从token中获取用户名
        String username = JwtTokenUtil.getUsername(token, audience.getBase64Secret());
        try {
            //判断redis中的获取的token是否为空或者用户携带的token是否跟redis中的一致
            if (redisUtil.get(username) == null || !redisUtil.get(username).equals(token)) {
                throw new CustomException(ResultCode.PERMISSION_EXPIRE);
            }
        } catch (RedisConnectionFailureException exception) {
            throw new CustomException(ResultCode.REDIS_ERROR);
        }


        //获取请求路径
        String requestURI = request.getRequestURI();
        String[] split = requestURI.split("/");
        //从token中获取用户角色名
        Integer roleValue = JwtTokenUtil.getRoleValue(token, audience.getBase64Secret());
        String desc = RoleEnum.getEnum(roleValue).getDesc();
        if (split[1].equals(desc)) {
            //从token中获取用户id
            Integer userId = JwtTokenUtil.getUserId(token, audience.getBase64Secret());

            //查询角色对应的组件权限
            Set<String> componentByRoleId = userService.getComponentByRole(roleValue);
            //查询用户对应的组件权限
            Set<String> componentByUserIdAndRoleId = userService.getComponentByUserIdAndRole(userId, roleValue);
            if (componentByRoleId != null && componentByRoleId.contains(split[2])) {
                return true;
            } else if (componentByUserIdAndRoleId != null && componentByUserIdAndRoleId.contains(split[2])) {
                return true;
            } else {
                log.info("### 用户无权限 ###");
                throw new CustomException(ResultCode.PERMISSION_UNAUTHORISE);
            }
        } else {
            log.info("### 用户无权限 ###");
            throw new CustomException(ResultCode.PERMISSION_UNAUTHORISE);
        }
    }

}

