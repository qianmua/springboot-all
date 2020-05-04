package pres.qianmuna.redis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by IntelliJ IDEA.
 *
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/5/3
 * @time 20:02
 */
@SpringBootApplication
public class SpringbootRedisTestApplication {

    /*
    * springboot 2.x 之后 jedis 被替换成lettuce
    * jedis 采用直连，多个线程操作不安全 ， 避免 就使用jedis pool 线程池 BIO
    * lettuce 采用netty 实例可以在多线程中共享没不存在线程不安全的情况，可以减少线程数据 NIO
    *
    *
    * springboot 所有配置类都有一个自动配置文类 spring.factories
    * 绑定一个properties 配置文件
    *
    * */

    public static void main(String[] args) {
        SpringApplication.run( SpringbootRedisTestApplication.class,args);
    }
}
