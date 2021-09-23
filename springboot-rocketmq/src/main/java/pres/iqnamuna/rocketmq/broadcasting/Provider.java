package pres.iqnamuna.rocketmq.broadcasting;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/5/30  12:44
 * @description :
 */
public class Provider {

    public static void main(String[] args) throws MQClientException, UnsupportedEncodingException, RemotingException, InterruptedException, MQBrokerException {

        DefaultMQProducer provider = new DefaultMQProducer("group_demo_broad_provider");

        provider.setNamesrvAddr("127.0.0.1:9876");

        provider.start();

        /// 批量 消息
        List<Message> list = new ArrayList<>(16);
        for (int i = 0; i < 10; i++) {

            Message message = new Message("topic_broad_hello_world" , // 消息 主题
                    "Tags", // 过滤用 ， 消息 标签
                    "primary_key" + i, // 消息 唯一键
                    ("hello wolrd rocketMQ" + i ).getBytes(RemotingHelper.DEFAULT_CHARSET)); //消息

            list.add(message);
        }
        // 批量发送消息
        // 发送消息
        SendResult result = provider.send(list);
        // result
        System.out.println(result);

//        provider.queryMessage("topic_broad_hello_world" , "primary_key1" , 10 , 1L , 1L);

        // 关闭掉
        provider.shutdown();

    }
}
