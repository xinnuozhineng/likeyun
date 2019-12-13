package net.likeyun.common.mapper;

import net.likeyun.common.entity.LoginInfo;
import net.likeyun.common.entity.User;
import net.likeyun.common.enum_base.RoleEnum;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface LoginMapper {

    User findLoginUser(@Param("username") String username, @Param("enumList") List<RoleEnum> enumList);

    void updateLoginTime(@Param("user") User user);

    void createUserLoginLog(@Param("login") LoginInfo loginInfo);
}
