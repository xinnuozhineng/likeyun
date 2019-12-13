package net.likeyun.common.service.impl;

import net.likeyun.common.entity.User;
import net.likeyun.common.enum_base.RoleEnum;
import net.likeyun.common.exception.CustomException;
import net.likeyun.common.mapper.UserMapper;
import net.likeyun.common.response.ResultCode;
import net.likeyun.common.service.UserService;
import net.likeyun.common.util.EncryptUtil;
import net.likeyun.common.util.PhoneNumerUtil;
import net.likeyun.common.util.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

/**
 * @Description: 用户业务层
 * @Author: lfy
 * @Date: 2019/11/29 15:14
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    @Transactional(readOnly = true)
    public Set<String> getComponentByRole(Integer roleValue) {
        return userMapper.getComponentByRole(roleValue);
    }

    @Override
    @Transactional(readOnly = true)
    public Set<String> getComponentByUserIdAndRole(Integer userId, Integer roleValue) {
        return userMapper.getComponentByUserIdAndRole(userId,roleValue);
    }

    @Override
    public void register(User user) {
        //判断账号是否存在
        this.isExist(user.getUsername());
        //设置加密密码
        user.setPassword(EncryptUtil.encryptPassword(user));
        //设置创建时间
        user.setCreateTime(TimeUtil.currentTimeStamp());
        userMapper.register(user);
    }

    @Override
    public void isExist(String username) {
        if (!PhoneNumerUtil.isMobileExact(username)) {
            throw new CustomException(ResultCode.USER_FORMAT_ERROR);
        }
        List<Integer> count = userMapper.isExist(username, RoleEnum.getEnumList());
        if (count.size() > 0) {
            throw new CustomException(ResultCode.USER_HAS_EXISTED);
        }
    }
}
