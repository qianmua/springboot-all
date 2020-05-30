package pres.iqnamuna.rocketmq.transaction;

import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/5/30  14:27
 * @description :
 */
public class LocalTransactionImpl implements TransactionListener {


    /*
    * 对应 的状态信息
    * key 事务ID
    * value 当前事务 状态
    * */
    private ConcurrentHashMap<String , Integer> local = new ConcurrentHashMap<>();

    /**
     * 执行 本地事务
     * @param message
     * @param o
     * @return
     */
    @Override
    public LocalTransactionState executeLocalTransaction(Message message, Object o) {
        /*
         * 业务 处理 本地事务
         *
         * */

        // 事务ID
        String id = message.getTransactionId();
        // 执行业务
        System.out.println("transaction -> id is -> ++ " + id);

        // 状态
        // 0
        // 1
        // 2
        local.put(id , 0);



        try {
            System.out.println("exec");
            TimeUnit.SECONDS.sleep(5);
            // 业务
            System.out.println("SUCCESS");
            // 状态码
            local.put(id , 1);
        } catch (InterruptedException e) {
            e.printStackTrace();
            // 异常 回滚
            local.put(id , 2);
            return LocalTransactionState.ROLLBACK_MESSAGE;
        }

        // 提交事务
        return LocalTransactionState.COMMIT_MESSAGE;
    }


    /**
     * 消息 回查
     * @param messageExt
     * @return
     */
    @Override
    public LocalTransactionState checkLocalTransaction(MessageExt messageExt) {

        /*
        * 业务 回查
        *
        * */

        // 得到 事务状态信息

        // 得到事务ID
        String id = messageExt.getTransactionId();

        //options
        Integer integer = local.get(id);// 得到状态信息


        // 查看
        System.out.println(" transaction ID -> " + id);
        System.out.println(" local status -> " + integer);

        // 状态
        switch (integer){
            case 0:
                return LocalTransactionState.UNKNOW;
            case 1:
                return LocalTransactionState.COMMIT_MESSAGE;
            case 2:
                return LocalTransactionState.ROLLBACK_MESSAGE;
        }

        return LocalTransactionState.UNKNOW;
    }
}
