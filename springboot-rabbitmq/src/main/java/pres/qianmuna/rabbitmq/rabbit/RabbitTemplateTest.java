package pres.qianmuna.rabbitmq.rabbit;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by IntelliJ IDEA.
 *
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/5/6
 * @time 17:51
 */
/**
 * 生产者
 * */
@RestController
public class RabbitTemplateTest {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping("/sendmail")
    public void test(){


        /**
         * 参数：
         * 交换机名称
         * routingKey
         * */
        rabbitTemplate.convertAndSend("ename" , "key" , "msg");

    }
}
