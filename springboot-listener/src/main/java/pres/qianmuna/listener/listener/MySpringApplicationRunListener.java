package pres.qianmuna.listener.listener;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * Created by IntelliJ IDEA.
 *
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/5/15
 * @time 13:58
 */
public class MySpringApplicationRunListener implements SpringApplicationRunListener {


    /**
     * 必须有一个有参构造
     * 实现SpringApplicationRunListener
     * @param springApplication
     * @param args
     */
    public MySpringApplicationRunListener(SpringApplication springApplication , String[] args){

    }

    /**
     * 监听springboot
     */
    @Override
    public void starting() {
        System.out.println("start");
    }

    /**
     * 监听启动环境细心
     * @param environment
     */
    @Override
    public void environmentPrepared(ConfigurableEnvironment environment) {
        System.out.println("监听环境信息");
    }

    @Override
    public void contextPrepared(ConfigurableApplicationContext context) {
        System.out.println("IOC容器准备ok");
    }

    @Override
    public void contextLoaded(ConfigurableApplicationContext context) {
        System.out.println("容器环境加载完成");
    }
}
