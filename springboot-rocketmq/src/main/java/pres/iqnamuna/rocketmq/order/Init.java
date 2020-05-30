package pres.iqnamuna.rocketmq.order;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.io.UnsupportedEncodingException;

/**
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/5/30  13:41
 * @description :
 */
public class Init {

    /*
    * 有序消息 发送 生成
    *
    *
    *
    *
    * 有序消费
    *
    * 顺序消息生产者
    *
    * 消费队列
    *
    *
    * // 指定发送的消息下标
    *
    * */

    public static void main(String[] args) throws UnsupportedEncodingException, InterruptedException, RemotingException, MQClientException, MQBrokerException {



        DefaultMQProducer provider = new DefaultMQProducer("group_demo_provider");

        provider.setNamesrvAddr("127.0.0.1:9876");

        try {
            provider.start();
        } catch (MQClientException e) {
            e.printStackTrace();
        }



        /*
        * 改造
        * */

        /*
        * 参数
        *
        * 1、 发送的消息 信息
        * 2、 选中的消息队列名称 // 将所有消息队列传入
        * 3、 指定下标
        * */

        // 发送消息
        for (int i = 0; i < 5; i++) {
            Message message = new Message("topic_provider_hello_world" , // 消息 主题
                    "Tags", // 过滤用 ， 消息 标签
                    "primary_key" + i, // 消息 唯一键
                    ("hello world rocketMQ " + i ).getBytes(RemotingHelper.DEFAULT_CHARSET)); //消息

            SendResult result = provider.send(message, (mqs  , msg , arg) ->
                            // 得到 队列下标
                            // 得到 指定 队列 下标
                            mqs.get( (Integer) arg)
                    ,1);
            // result
            System.out.println(result);

        }


        // 关闭掉
        provider.shutdown();

    }
}
