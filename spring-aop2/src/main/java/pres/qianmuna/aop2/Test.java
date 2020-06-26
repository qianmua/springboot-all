package pres.qianmuna.aop2;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;
import pres.qianmuna.aop2.inter.Service;

/**
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/6/25  21:15
 * @description : aop instance
 */
public class Test {

    public static void main(String[] args) {
        ClassPathResource resource = new ClassPathResource("applicationContext.xml");
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        reader.loadBeanDefinitions(resource);

        // 代理对象
        // doGetBean // 分支
        // getSingleton // 底层 单例池 中 获取 // 缓存
        // createBeanInstance // 完成了 bean 的创建
        // AbsBeanFactory 类 下 得到 bean对象
        // -> getObjectForBeanInstance //分支
        // self or FactoryBean ->
        //
        Service aop = (Service) factory.getBean("aop");


        aop.say();

    }
}
