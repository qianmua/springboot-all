package pres.qianmuna.cache2;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * Created by IntelliJ IDEA.
 *
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/5/15
 * @time 14:14
 */
@SpringBootApplication
@EnableCaching
@MapperScan("pres.qianmuna.cache2.mapper")
public class Cache2DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(Cache2DemoApplication.class, args);
    }
}
