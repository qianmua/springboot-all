package pres.qianmuna.spring.factory;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;
import pres.qianmuna.spring.aspect.TestAnnotatin;

import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 *
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/5/8
 * @time 21:17
 */
public class HelloImportBeanDefinitionRegistart implements ImportBeanDefinitionRegistrar {

    /**
     * bean注册工厂
     * */

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        //Bean factory -> spring bean 工厂 是一个特殊的bean
        //实现了接口，得到他自己需要加上& ，不加的话是返回它实现方法的那个Object


        //得到注解信息
        Map<String, Object> attributes = importingClassMetadata.getAnnotationAttributes(TestAnnotatin.class.getName());
        System.out.println(attributes);

        //手动注册
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(HelloFactoryBean.class);
        //得到了springBean类信息
        BeanDefinition definition = builder.getBeanDefinition();

        //得到所有的构造方法参数
//        definition.getConstructorArgumentValues();
        //添加构造参数
//        definition.getConstructorArgumentValues().addIndexedArgumentValue();

        //从definition得到Bean 并且注册
        registry.registerBeanDefinition("helloFactoryBean" , definition);
    }
}
