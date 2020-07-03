package pres.qianmuna.spring.bean;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;
import pres.qianmuna.spring.aspect.DemoScan;
import pres.qianmuna.spring.factory.HelloImportBeanDefinitionRegistart;

/**
 * Created by IntelliJ IDEA.
 *
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/5/2
 * @time 23:16
 */
@Configuration
// 注意ComponentScan 和 MapperScan
@ComponentScan("pres.qianmuna.spring")
//import
@Import(HelloImportBeanDefinitionRegistart.class)
@DemoScan("pres.hjc.qqq")
@EnableAspectJAutoProxy
public class UserModel {
    /**
     * spring自己会把这个config注册
     * */
}
