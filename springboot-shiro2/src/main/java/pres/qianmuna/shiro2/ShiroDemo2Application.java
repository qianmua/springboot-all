package pres.qianmuna.shiro2;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/6/16  15:29
 * @description :
 */
@SpringBootApplication
@EnableTransactionManagement
@MapperScan( basePackages = "pres.qianmuna.shiro2.mapper")
public class ShiroDemo2Application {

    public static void main(String[] args) {
        SpringApplication.run(ShiroDemo2Application.class , args);
    }
}
