package pres.qianmuna.quzrtz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Created by IntelliJ IDEA.
 *
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/5/27
 * @time 12:21
 */
@SpringBootApplication
@EnableScheduling
public class Quartz2DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run( Quartz2DemoApplication.class , args);
    }
}
