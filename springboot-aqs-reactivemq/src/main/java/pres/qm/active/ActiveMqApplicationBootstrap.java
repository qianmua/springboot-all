package pres.qm.active;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

/**
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/10/21  14:08
 * @description :
 */
@SpringBootApplication
@EnableJms
public class ActiveMqApplicationBootstrap {

    public static void main(String[] args) {
        SpringApplication.run(ActiveMqApplicationBootstrap.class,args);
    }
}
