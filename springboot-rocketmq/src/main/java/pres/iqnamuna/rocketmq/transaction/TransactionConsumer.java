package pres.iqnamuna.rocketmq.transaction;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerOrderly;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.remoting.common.RemotingHelper;

import java.io.UnsupportedEncodingException;

/**
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/5/30  13:04
 * @description :
 */
public class TransactionConsumer {

    public static void main(String[] args) throws MQClientException {

        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("group_consumer_demo");

        consumer.setNamesrvAddr("127.0.0.1:9876");

        // 消息 最大 拉去数量
        consumer.setConsumeMessageBatchMaxSize(2);

        consumer.subscribe("topic_provider_hello_world", // 订阅 主题
                "Tags || TagA" // or * // 晓飞规则·
                );


        /*
        * 有序 消费
        *
        * */
        //MessageListenerOrderly 有序 消费
        //MessageListenerConcurrently 不好
        consumer.setMessageListener((MessageListenerOrderly) (list, consumeConcurrentlyContext) -> {
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

                    System.out.println("topic -- >" + topic);
                    System.out.println("tags -- >" + tags);
                    System.out.println("str -- >" + str);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                    // 消息失败 // 重试
//                    return ConsumeConcurrentlyStatus.RECONSUME_LATER;
                }
            });
            // 消息发送成功S
            return ConsumeOrderlyStatus.SUCCESS;
        });

        consumer.start();

    }
}
