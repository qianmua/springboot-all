package pres.qm.kafka.chapter2;


import org.apache.kafka.clients.producer.ProducerInterceptor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.Configurable;

import java.util.Map;

/**
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/9/14  21:52
 * @description :
 */
public class ProducerinterceptorPrefix implements ProducerInterceptor<String, String > {

    private volatile long sendSuccess = 0;
    private volatile long sendFailule = 0;


    @Override
    public void configure(Map<String, ?> map) {

    }

    @Override
    public ProducerRecord<String, String> onSend(ProducerRecord<String, String> producerRecord) {
        String s = "prefix-" + producerRecord.value();

        return new ProducerRecord<>( producerRecord.topic() , producerRecord.partition() , producerRecord.timestamp(),
                producerRecord.key() , s , producerRecord.headers());

    }

    @Override
    public synchronized void onAcknowledgement(RecordMetadata recordMetadata, Exception e) {
        if (e == null)
            sendSuccess++;
        else
            sendFailule++;
    }

    @Override
    public void close() {
        double v = (double) sendSuccess / (sendSuccess + sendFailule);
        System.out.println(String.format("%f" , v*100) + "%");
    }
}
