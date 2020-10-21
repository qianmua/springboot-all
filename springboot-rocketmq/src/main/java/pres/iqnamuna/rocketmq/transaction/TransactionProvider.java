package pres.iqnamuna.rocketmq.transaction;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.*;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.io.UnsupportedEncodingException;
import java.util.concurrent.*;

/**
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/5/30  13:41
 * @description :
 */
public class TransactionProvider {


    public static void main(String[] args) throws UnsupportedEncodingException, InterruptedException, RemotingException, MQClientException, MQBrokerException {

//        DefaultMQProducer provider = new DefaultMQProducer("group_demo_provider");

        // group name
        TransactionMQProducer provider = new TransactionMQProducer("group_demo_transaction_provider");

        provider.setNamesrvAddr("127.0.0.1:9876");

        /*
        * 指定 消息 监听对象 // 执行本地事务和消息查看
        *
        * */
        provider.setTransactionListener(new LocalTransactionImpl());


        /*
        * 创建 线程池
        * */
        ExecutorService service = new ThreadPoolExecutor(2,
                5,
                20,
                TimeUnit.SECONDS,
                // 有界队列
                new ArrayBlockingQueue<>(20),
                /*r -> {
                    Thread thread = new Thread(r);
                    thread.setName("client-transaction-msg-check-thread");
                    return thread;
                }*/
                Executors.defaultThreadFactory()
                // 拒绝策略
        );

        // 将线程池 交给 provider
        provider.setExecutorService(service);

        try {
            provider.start();
        } catch (MQClientException e) {
            e.printStackTrace();
        }

        // 发送消息
        Message message = new Message("topic_provider_transaction_hello_world" , // 消息 主题
                "Tags", // 过滤用 ， 消息 标签
                "primary_key_T" , // 消息 唯一键
                ("hello world rocketMQ transaction " ).getBytes(RemotingHelper.DEFAULT_CHARSET)); //消息

        /*
        * 发送 事务 消息
        *
        * */
        TransactionSendResult result = provider.sendMessageInTransaction( message,
                "trans-tags");


        // result
        System.out.println(result);

        // 关闭掉
        provider.shutdown();

    }
}
