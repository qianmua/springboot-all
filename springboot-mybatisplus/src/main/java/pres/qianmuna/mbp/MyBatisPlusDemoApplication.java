package pres.qianmuna.mbp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by IntelliJ IDEA.
 *
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/5/6
 * @time 18:28
 */
@SpringBootApplication
@MapperScan("pres.qianmuna.mbp.mapper")
public class MyBatisPlusDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyBatisPlusDemoApplication.class,args);
    }

}
