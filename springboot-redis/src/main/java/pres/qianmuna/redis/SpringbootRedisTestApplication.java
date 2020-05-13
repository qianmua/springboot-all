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

    /**
     * jvm锁 与 分布式锁
     *
     * jvm 是 在本地自己的jvm里面拿锁
     * 分布式锁 就是 我们 的项目程序 向redis去拿锁
     * 两个锁的地方不一样
     *
     * redis是基于单线程的所以可以实现分布式锁
     * 同一时间是只有一个响应
     *
     * 可以 使用setnx 实现 （数据库有value 则 返回no ， 没有 就创建  相当于加锁 ）
     *
     * 解锁 ： 使用守护线程 去监听过期时间，当这个资源被释放时，守护线程也会挂掉，redis上面就会过期就释放了锁
     *
     * */

    /*
    * springboot 2.x 之后 jedis 被替换成lettuce
    * jedis 采用直连，多个线程操作不安全 ， 避免 就使用jedis pool 线程池 BIO
    * lettuce 采用netty 实例可以在多线程中共享没不存在线程不安全的情况，可以减少线程数据 NIO
    *
    *
    * springboot 所有配置类都有一个自动配置文类 spring.factories
    * 绑定一个properties 配置文件
    *
    *
    * */

    public static void main(String[] args) {
        SpringApplication.run( SpringbootRedisTestApplication.class,args);
    }
}
