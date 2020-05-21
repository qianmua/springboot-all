package pres.qianmuna.dubbo;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by IntelliJ IDEA.
 *
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/5/21
 * @time 18:24
 */

@SpringBootApplication
@EnableDubbo
public class DubboDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DubboDemoApplication.class , args);
    }
}
