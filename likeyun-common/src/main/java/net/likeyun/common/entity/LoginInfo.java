package net.likeyun.common.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import net.likeyun.common.enum_base.RoleEnum;

/**
 * @Description: 登录信息实体
 * @Author: lfy
 * @Date: 2019/12/10 11:04
 */
@Setter
@Getter
@ToString
public class LoginInfo {
    private Integer id;

    private String username;

    private String realname;

    private RoleEnum role;

    private String ip;

    private String address;

    private Integer loginTime;
}
