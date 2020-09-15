package pres.qm.kafka.chapter4;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

/**
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/9/15  21:10
 * @description :
 */
public class ProducerTransactionSend {
    public static final String topic = "qm";
    public static final String brokerList = "127.0.0.1";
    public static final String transactionId = "transactionId";

    public static void main(String[] args) {
        Properties properties = new Properties();

        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG , StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG , StringSerializer.class.getName());


        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG , brokerList);

        properties.put(ProducerConfig.TRANSACTIONAL_ID_CONFIG ,transactionId);
        properties.put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG , true);

        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);

        // init trans
        producer.initTransactions();
        //begin
        producer.beginTransaction();


        try{

            ProducerRecord<String, String> record = new ProducerRecord<>(topic, "message-1");
            producer.send(record);
            // commit
            producer.commitTransaction();
        }catch (Exception e){
            e.printStackTrace();
            producer.abortTransaction();
        }



        producer.close();
    }
}
