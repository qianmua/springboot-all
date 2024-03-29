package pres.iqnamuna.rocketmq.broadcasting;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;
import org.apache.rocketmq.remoting.common.RemotingHelper;

import java.io.UnsupportedEncodingException;

/**
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/5/30  13:04
 * @description :
 */
public class Consumer {

    /*
    * 消费 有延迟？？
    *
    * 延时
    *
    * 去队列 数据
    *
    *
    *
    * */

    public static void main(String[] args) throws MQClientException {

        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("group_demo_broad_consumer");

        consumer.setNamesrvAddr("127.0.0.1:9876");

        // 默认是集群消费模式

        //改为 广播
        consumer.setMessageModel(MessageModel.BROADCASTING);

        // 消息 最大 拉去数量
        consumer.setConsumeMessageBatchMaxSize(3);

        consumer.subscribe("topic_broad_hello_world", // 订阅 主题
                "*" // or * // 消费规则·
                );
        /*
        * 有序 消费
        *
        * */
        //MessageListenerOrderly 有序 消费
        //MessageListenerConcurrently 不好
        consumer.setMessageListener((MessageListenerConcurrently) (list, consumeConcurrentlyContext) -> {
            //得到消息
            list.forEach( r -> {
                try {
                    // 的到主题
                    String topic = r.getTopic();
                    // 得到 标签
                    String tags = r.getTags();
                    // 得到 body info
                    byte[] body = r.getBody();
                    String str = new String(body , RemotingHelper.DEFAULT_CHARSET);

                    System.out.println("B----topic -- >" + topic);
                    System.out.println("B----tags -- >" + tags);
                    System.out.println("B----str -- >" + str);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                    // 消息失败 // 重试
//                    return ConsumeConcurrentlyStatus.RECONSUME_LATER;
                }
            });
            // 消息发送成功S
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        });

        consumer.start();

    }
}
