package pres.qm.es;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/9/9  1:16
 * @description :
 */
@SpringBootApplication
@ComponentScan( basePackages = "pres.qm")
public class ElasticsearchApplicationBoosstrap {


    public static void main(String[] args) {
        SpringApplication.run(ElasticsearchApplicationBoosstrap.class , args);
    }
}
