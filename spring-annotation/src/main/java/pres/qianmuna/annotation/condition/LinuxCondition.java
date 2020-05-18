package pres.qianmuna.annotation.condition;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;


/**
 * Created by IntelliJ IDEA.
 *
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/5/18
 * @time 11:44
 */
public class LinuxCondition implements Condition {

    /*
     * 判断条件注册
     * */


    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        //上下文信息
        Environment environment = context.getEnvironment();

        //操作系统
        String property = environment.getProperty("os.name");
        if (property.contains("linux")){
            return true;
        }
        return false;
    }

}
