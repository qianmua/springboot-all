package pres.qm.active;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.jms.Queue;


/**
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/10/21  14:08
 * @description : 流量削峰
 */
@SpringBootApplication
@EnableJms
@EnableTransactionManagement
public class ActiveMqApplicationBootstrap {

    /**
     * node jms
     * @return jms queue
     */
    @Bean
    public Queue queue(){
        return new ActiveMQQueue("demo_req");
    }

    public static void main(String[] args) {
        SpringApplication.run(ActiveMqApplicationBootstrap.class,args);
    }
}
