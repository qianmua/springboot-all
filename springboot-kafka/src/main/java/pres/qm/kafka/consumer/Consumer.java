package pres.qm.kafka.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/9/9  0:46
 * @description :
 */

@Component
public class Consumer {

    @KafkaListener(topics = "msg")
    public void consumer(ConsumerRecord consumerRecord){
        Optional<Object> optionalO = Optional.ofNullable(consumerRecord.value());

        if (optionalO.isPresent()) {
            Object o = optionalO.get();
            System.out.println(o);
        }

    }
}
