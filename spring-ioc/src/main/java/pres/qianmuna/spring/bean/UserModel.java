package pres.qianmuna.spring.bean;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
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
@ComponentScan("pres.qianmuna.spring")
//import
@Import(HelloImportBeanDefinitionRegistart.class)
public class UserModel {
    /**
     * spring自己会把这个config注册
     * */
}
