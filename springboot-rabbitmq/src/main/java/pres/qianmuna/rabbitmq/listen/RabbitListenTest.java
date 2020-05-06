package pres.qianmuna.rabbitmq.listen;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Created by IntelliJ IDEA.
 *
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/5/6
 * @time 18:00
 */
@Component
public class RabbitListenTest {

    @RabbitListener( queues = {"ename"})
    public void mailName(String msg, Message message , Channel channel){
        System.out.println(msg);
        System.out.println(message);
        System.out.println(channel);
    }

}
