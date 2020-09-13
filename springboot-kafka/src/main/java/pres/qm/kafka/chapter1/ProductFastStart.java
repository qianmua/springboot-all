package pres.qm.kafka.chapter1;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

/**
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/9/13  12:56
 * @description :
 */
public class ProductFastStart {

    public static void main(String[] args) {
        Properties properties = new Properties();
        // 序列化器
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG ,"org.apache.kafka.common.serialization.Serializer");

        // 重试
        properties.put(ProducerConfig.RETRIES_CONFIG ,  10);

        // 值序列化器
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG , "org.apache.kafka.common.serialization.Serializer");

        // 集群地址
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG , "localhost:9092");

        // product




    }
}
