package pres.qm.kafka.chapter1;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

/**
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/9/13  12:56
 * @description :
 */
public class ConsumerFastStart {

    public static void main(String[] args) {
        Properties properties = new Properties();
        // 序列化器
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        // 值序列化器
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG , "127.0.0.1:9092");
        // 消费者组
        properties.put(ConsumerConfig.GROUP_ID_CONFIG , "group.qm");

        // 消息订阅
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(properties);
        // 订阅主题
        consumer.subscribe(Collections.singletonList("qm_kafka"));

        while (true){
            // 消息 订阅
            ConsumerRecords<String, String> poll = consumer.poll(Duration.ofMillis(1000L));
            poll.forEach(v1 -> System.out.println(v1.value()));

        }
    }
}
