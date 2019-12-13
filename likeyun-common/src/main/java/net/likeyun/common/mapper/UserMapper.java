package net.likeyun.common.mapper;

import net.likeyun.common.entity.User;
import net.likeyun.common.enum_base.RoleEnum;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

@Mapper
public interface UserMapper {

    Set<String> getComponentByRole(@Param("roleValue") Integer roleValue);

    Set<String> getComponentByUserIdAndRole(@Param("userId") Integer userId, @Param("roleValue") Integer roleValue);

    void register(User user);

    List<Integer> isExist(@Param("username") String username, @Param("enumList") List<RoleEnum> enumList);
}
