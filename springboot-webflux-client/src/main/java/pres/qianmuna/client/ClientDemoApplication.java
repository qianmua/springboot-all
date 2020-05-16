package pres.qianmuna.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * Created by IntelliJ IDEA.
 *
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/5/16
 * @time 18:31
 */
@SpringBootApplication
@EnableMongoRepositories
public class ClientDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClientDemoApplication.class , args);
    }
}
