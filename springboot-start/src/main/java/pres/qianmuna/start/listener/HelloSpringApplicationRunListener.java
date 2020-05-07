package pres.qianmuna.start.listener;

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
 * @date 2020/5/7
 * @time 17:54
 */
public class HelloSpringApplicationRunListener implements SpringApplicationRunListener {

    public HelloSpringApplicationRunListener(SpringApplication application , String[] args){

    }

    /**
     * IOC创建之前
     */
    @Override
    public void starting() {
        System.out.println("HelloSpringApplicationRunListener.............run.........");
    }

    /**
     * 基础环境准备完成
     * @param environment
     */
    @Override
    public void environmentPrepared(ConfigurableEnvironment environment) {
        System.out.println("环境准备完成");
        System.out.println("得到环境的东西 ->" + environment.getSystemProperties().get("os.name"));

    }

    /**
     * IOC加载完
     * @param context
     */
    @Override
    public void contextPrepared(ConfigurableApplicationContext context) {
        System.out.println("IOC 容器准备ok");
    }

    @Override
    public void contextLoaded(ConfigurableApplicationContext context) {
        System.out.println("IOC 加载完成");
        }

}
