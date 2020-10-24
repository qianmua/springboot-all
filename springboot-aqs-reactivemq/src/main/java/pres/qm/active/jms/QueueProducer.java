package pres.qm.active.jms;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/10/24  22:22
 * @description :
 */
public class QueueProducer {

    // 默认用户名
    public static final String USER_NAME = ActiveMQConnectionFactory.DEFAULT_USER;
    // 默认psw
    public static final String PASSWORD = ActiveMQConnectionFactory.DEFAULT_PASSWORD;
    // 默认url
    public static final String ACTIVE = "tcp://139.196.166.75:61617";
    // 发送条数
    public static final int MessageNum = 10;

    public static void main(String[] args) {
        ConnectionFactory connectionFactory;

        Connection connection = null;
        // 消息 接收 发送线程
        Session session;
        // 消息目的地
        Destination destination;
        // 消息生产者
        MessageProducer messageProducerl;
        // 链接工厂
        connectionFactory = new ActiveMQConnectionFactory(USER_NAME , PASSWORD , ACTIVE);

        try {
            // 创建链接
            connection = connectionFactory.createConnection();
            connection.start();
            // 创建session
            session = connection.createSession(Boolean.TRUE  , Session.AUTO_ACKNOWLEDGE);
            // 消息目的地
            destination = session.createQueue("FirstQueue");
            // 告诉生产者发送消息目的地
            messageProducerl = session.createProducer(destination);

            // 发送消息
            sendMessage(session,messageProducerl);

            session.commit();

        } catch (JMSException e) {
            e.printStackTrace();
        }finally {
            if (connection != null){
                try {
                    connection.close();
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * send message
     * @param session
     * @param messageProducerl
     * @throws JMSException
     */
    private static void sendMessage(Session session, MessageProducer messageProducerl) throws JMSException {
        for (int i = 0; i < QueueProducer.MessageNum; i++) {
            TextMessage textMessage = session.createTextMessage("send message" + i);
            System.out.println("send message is " + i);
            messageProducerl.send(textMessage);
        }
    }
}
