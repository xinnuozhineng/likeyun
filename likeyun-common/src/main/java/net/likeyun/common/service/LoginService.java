package net.likeyun.common.service;

import net.likeyun.common.entity.User;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public interface LoginService {
    User login(User user, HttpServletRequest request) throws IOException;
}
