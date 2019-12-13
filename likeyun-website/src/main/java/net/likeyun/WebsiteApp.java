package net.likeyun;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

/**
 *  项目启动
 * @author lfy
 * @date 2019/12/3 13:24
 */
@SpringBootApplication
@Import(CommonConfig.class)
public class WebsiteApp {
    public static void main(String[] args) {
        SpringApplication.run(WebsiteApp.class, args);
    }
}
