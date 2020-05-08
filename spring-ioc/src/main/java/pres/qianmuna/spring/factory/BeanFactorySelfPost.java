package pres.qianmuna.spring.factory;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.ConstructorArgumentValues;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.stereotype.Component;
import pres.qianmuna.spring.bean.B;

/**
 * Created by IntelliJ IDEA.
 *
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/5/3
 * @time 15:51
 */
@Component
public class BeanFactorySelfPost implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory factory) throws BeansException {
        //将第三步map可以传到这里，BeanFactory ，bean工厂！
        System.out.println("init bean");
        //this.beanDefinitionMap.get(beanName)
//        BeanDefinition a = configurableListableBeanFactory.getBeanDefinition("b");
        //只能update 不可以修改哦
//        a.setBeanClassName("b");

        //从池子中拿到这个孵化对象
        GenericBeanDefinition genericBeanDefinition =
                (GenericBeanDefinition) factory.getBeanDefinition("a");

//        genericBeanDefinition.setBeanClass(B.class);


        GenericBeanDefinition genericBeanDefinition1 =
                (GenericBeanDefinition) factory.getBeanDefinition("c");

        //修改注册参数，
        ConstructorArgumentValues values = new ConstructorArgumentValues();
        //有参构造 index 位置 ， value 参数
        values.addIndexedArgumentValue(0,"name");
        genericBeanDefinition1.setConstructorArgumentValues(values);

    }

}
