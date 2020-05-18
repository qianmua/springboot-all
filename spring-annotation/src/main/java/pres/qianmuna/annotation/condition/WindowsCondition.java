package pres.qianmuna.annotation.condition;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
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
public class WindowsCondition implements Condition {


    /**
     *
     * 判断条件上下文
     *
     * @param context
     * @param metadata
     * @return
     */
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        // ioc bean工厂
        ConfigurableListableBeanFactory factory = context.getBeanFactory();

        //类加载器
        ClassLoader classLoader = context.getClassLoader();

        //上下文信息
        Environment environment = context.getEnvironment();

        //bean 定义的注册类
        BeanDefinitionRegistry registry = context.getRegistry();

        //操作系统
        String property = environment.getProperty("os.name");
        //包含
        if (property.contains("win10")){
            return true;
        }

        return false;
    }
}
