package pres.qianmuna.rabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by IntelliJ IDEA.
 *
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/5/5
 * @time 23:03
 */
@Configuration
public class RabbitmqConfig {


    /**
     * 声明交换机
     * @return
     */
    @Bean("ename")
    public Exchange exchange(){
        return ExchangeBuilder.topicExchange("ename").delayed().build();
    }

    /**
     * 声明队列
     * @return
     */
    @Bean("qname")
    public Queue queue(){
        return new Queue("qname");
    }

    /**
     * 绑定队列
     * @param queue
     * @param exchange
     * @return
     */
    @Bean
    public Binding binding(@Qualifier("qname") Queue queue,
                           @Qualifier("ename") Exchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with("key").noargs();
    }


}
