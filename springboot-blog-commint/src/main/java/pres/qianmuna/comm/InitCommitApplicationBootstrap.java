package pres.qianmuna.comm;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/7/9  18:01
 * @description :
 */

@SpringBootApplication
@MapperScan( basePackages = {"pres.qianmuna.comm.mapper"})
public class InitCommitApplicationBootstrap {

    public static void main(String[] args) {
        SpringApplication.run(InitCommitApplicationBootstrap.class , args);
    }
}
