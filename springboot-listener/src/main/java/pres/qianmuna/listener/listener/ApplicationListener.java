package pres.qianmuna.listener.listener;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * Created by IntelliJ IDEA.
 *
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/5/15
 * @time 13:57
 */
public class ApplicationListener implements ApplicationContextInitializer<ConfigurableApplicationContext> {
    /**
     * 监听IOC 容器 注册在spring.factories
     * @param applicationContext
     */
    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {

        System.out.println("ioc start");
        System.out.println("ioc start" + applicationContext);

    }
}
