package pres.qianmuna.webflux;

import org.mybatis.spring.annotation.MapperScan;
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
 * @time 13:38
 */
@SpringBootApplication
@MapperScan("pres.qianmuna.webflux.mapper")
@EnableMongoRepositories
public class WebfluxDemoApplication {

    /*
    * 响应式mongodb
    * */
    public static void main(String[] args) {
        SpringApplication.run(WebfluxDemoApplication.class , args);
    }
}
