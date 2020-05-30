package pres.iqnamuna.rocketmq.provider;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.io.UnsupportedEncodingException;

/**
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/5/30  12:44
 * @description :
 */
public class Init {

    public static void main(String[] args) throws MQClientException, UnsupportedEncodingException, RemotingException, InterruptedException, MQBrokerException {


        DefaultMQProducer provider = new DefaultMQProducer("group_demo_provider");

        provider.setNamesrvAddr("127.0.0.1:9876");

        provider.start();

        Message message = new Message("topic_provider_hello_world" , // 消息 主题
                "Tags", // 过滤用 ， 消息 标签
                "primary_key", // 消息 唯一键
                "hello wolrd rocketMQ".getBytes(RemotingHelper.DEFAULT_CHARSET)); //消息

        // 发送消息
        SendResult result = provider.send(message);
        // result
        System.out.println(result);


        // 关闭掉
        provider.shutdown();

    }
}
