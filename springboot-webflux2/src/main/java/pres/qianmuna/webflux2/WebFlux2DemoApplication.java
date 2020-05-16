package pres.qianmuna.webflux2;

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
 * @time 17:27
 */
@SpringBootApplication
@EnableMongoRepositories
public class WebFlux2DemoApplication {

    /*
    * web flux 两种开发模式
    * 1、 springboot controller requestMapping 样式
    *
    * 2、 routerFunction
    *
    * 流程
    * handler
    * router
    * httpHandle
    * server(netty servlet容器)
    * */

    /**
     * router function
     * 方式 处理请求
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(WebFlux2DemoApplication.class,args);
    }


}
