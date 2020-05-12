package pres.hjc.starter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by IntelliJ IDEA.
 *
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/5/12
 * @time 14:14
 */
@Configuration
//web
@ConditionalOnWebApplication
// 配置注入
@EnableConfigurationProperties( TestStarter.class)
public class MyServiceAutoConfiguration {

    @Autowired
    private TestStarter testStarter;

    @Bean
    public Hello hello(){
        Hello hello = new Hello();
        hello.setTestStarter(testStarter);
        return hello;
    }

}
