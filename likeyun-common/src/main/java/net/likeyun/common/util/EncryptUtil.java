package net.likeyun.common.util;

import net.likeyun.common.entity.User;
import net.likeyun.common.exception.CustomException;
import net.likeyun.common.response.ResultCode;

import java.security.MessageDigest;

/**
 * @Description: 密码加密工具类
 * @Author: lfy
 * @Date: 2019/12/6 16:18
 */
public final class EncryptUtil {
    private EncryptUtil() {
    }


    public static String encryptPassword(User user) {
        byte[] salt = (user.getRole().getDesc() + user.getUsername()).getBytes();
        byte[] password = user.getPassword().getBytes();
        String generatedPassword;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(salt);
            byte[] bytes = md.digest(password);
            StringBuilder sb = new StringBuilder();
            for (byte aByte : bytes) {
                sb.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
            }

            generatedPassword = sb.toString();
        } catch (Exception e) {
            throw new CustomException(ResultCode.SYSTEM_INNER_ERROR);
        }
        return generatedPassword;
    }
}
