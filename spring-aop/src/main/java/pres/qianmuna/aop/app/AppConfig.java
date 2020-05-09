package pres.qianmuna.aop.app;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

/**
 * Created by IntelliJ IDEA.
 *
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/5/9
 * @time 13:09
 */
@ComponentScan("pres.qianmuna.aop")
@Configuration
//必须开启？ 是的。。。
@EnableAspectJAutoProxy
public class AppConfig {
}
