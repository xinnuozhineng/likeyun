package net.likeyun.common.annotation;

import java.lang.annotation.*;

/**
 * @Description: JWT验证忽略注解
 * @Author: lfy
 * @Date: 2019/11/28 14:08
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface JwtIgnore {
}
