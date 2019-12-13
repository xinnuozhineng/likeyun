package net.likeyun.common.service.impl;

import net.likeyun.common.entity.LoginInfo;
import net.likeyun.common.entity.User;
import net.likeyun.common.enum_base.RoleEnum;
import net.likeyun.common.exception.CustomException;
import net.likeyun.common.mapper.LoginMapper;
import net.likeyun.common.response.ResultCode;
import net.likeyun.common.service.LoginService;
import net.likeyun.common.util.EncryptUtil;
import net.likeyun.common.util.IPUtil;
import net.likeyun.common.util.TimeUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @Description: 登录业务层
 * @Author: lfy
 * @Date: 2019/11/28 15:03
 */
@Service
@Transactional
public class LoginServiceImpl implements LoginService {

    @Resource
    private LoginMapper loginMapper;

    @Override
    public User login(User user, HttpServletRequest request){
        //查出用户
        User entity = loginMapper.findLoginUser(user.getUsername(), RoleEnum.getEnumList());
        if (entity != null) {
            user.setRole(entity.getRole());
        }
        //用户为空或密码不对返回自定义异常
        if (entity == null || !EncryptUtil.encryptPassword(user).equals(entity.getPassword())) {
            throw new CustomException(ResultCode.USER_LOGIN_ERROR);
        }
        Integer timeStamp = TimeUtil.currentTimeStamp();
        //获取登录者ip
        String ip = IPUtil.getIpAddress(request);
        //获取登录者ip所在地
        String address = IPUtil.getCityInfo(ip);
        //新建登录信息对象，设值
        LoginInfo loginInfo = new LoginInfo();
        BeanUtils.copyProperties(entity, loginInfo);
        loginInfo.setIp(ip);
        loginInfo.setAddress(address);
        loginInfo.setLoginTime(timeStamp);
        //创建登录信息日志
        loginMapper.createUserLoginLog(loginInfo);
        //设置登录时间
        entity.setLoginTime(timeStamp);
        //更改登录时间
        loginMapper.updateLoginTime(entity);

        return entity;
    }
}
