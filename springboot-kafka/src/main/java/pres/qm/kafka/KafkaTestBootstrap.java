package pres.qm.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pres.qm.kafka.product.Product;

import javax.annotation.PostConstruct;
import java.util.stream.IntStream;

/**
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/9/9  0:34
 * @description :
 */
@SpringBootApplication
public class KafkaTestBootstrap {

    @Autowired
    private Product product;

    @PostConstruct
    public void init(){
        IntStream.range(1 , 10)
                .forEach(v1 -> product.snedMessage(v1 + ""));
    }

    public static void main(String[] args) {
        SpringApplication.run(KafkaTestBootstrap.class , args);
    }
}
