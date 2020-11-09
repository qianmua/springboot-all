package pres.qm.shiro;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/11/9  14:08
 * @description :
 */
@SpringBootApplication
@MapperScan( basePackages = {"pres.qm.shiro.mapper"})
public class ShiroApplicationBootstrap {

    public static void main(String[] args) {
        SpringApplication.run(ShiroApplicationBootstrap.class , args);
    }
}
