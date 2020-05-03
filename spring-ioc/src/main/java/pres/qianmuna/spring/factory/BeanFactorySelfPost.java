package pres.qianmuna.spring.factory;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
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
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        //将第三步map可以传到这里，BeanFactory ，bean工厂！
        System.out.println("init bean");
        //this.beanDefinitionMap.get(beanName)
        BeanDefinition a = configurableListableBeanFactory.getBeanDefinition("b");
        a.setBeanClassName("b");
    }
}
