package net.likeyun.common.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import net.likeyun.common.annotation.JwtIgnore;
import net.likeyun.common.enum_base.RoleEnum;
import net.likeyun.common.enum_base.SexEnum;

/**
 * @Description: 用户实体
 * @Author: lfy
 * @Date: 2019/11/28 14:52
 */
@Setter
@Getter
@ToString
public class User {
    private Integer id;

    private String username;

    private String realname;

    private String password;

    private RoleEnum role;

    @JsonIgnore
    private Integer createTime;
    @JsonIgnore
    private Integer loginTime;
}
