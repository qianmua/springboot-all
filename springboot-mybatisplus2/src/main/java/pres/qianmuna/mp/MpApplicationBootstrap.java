package pres.qianmuna.mp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/9/2  20:11
 * @description :
 */
@SpringBootApplication
@MapperScan( basePackages = "pres.qianmuna.mp.mapper")
public class MpApplicationBootstrap {

    public static void main(String[] args) {
        SpringApplication.run(MpApplicationBootstrap.class , args);
    }
}
