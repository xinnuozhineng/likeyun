package net.likeyun.common.service;

import net.likeyun.common.entity.User;

import java.util.Set;

public interface UserService {
    Set<String> getComponentByRole(Integer roleValue);

    Set<String> getComponentByUserIdAndRole(Integer userId,Integer roleValue);

    void register(User user);

    void isExist(String username);
}
