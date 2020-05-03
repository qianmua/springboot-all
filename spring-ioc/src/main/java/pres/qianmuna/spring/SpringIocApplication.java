package pres.qianmuna.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import pres.qianmuna.spring.bean.A;
import pres.qianmuna.spring.bean.B;
import pres.qianmuna.spring.bean.UserModel;

/**
 * Created by IntelliJ IDEA.
 *
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/5/2
 * @time 22:50
 */
public class SpringIocApplication {

    public static void main(String[] args) {
        //初始化spring context
        //实例化bean
        // 初始化beanFactory
        AnnotationConfigApplicationContext
                //this();
                //register(componentClasses);
                //refresh(); // synchronized
                // 调用了 12个 方法
                //invokeBeanFactoryPostProcessors ->
                //registerBeanPostProcessors 注册你自己提供的config
                //用来扫描和给map中put
                // 实例了 map中spring内置的初始bean
                //spring 会先找出来所有实现了BeanFactoryPostProcessor以及BeanDefinitionRegistryPostProcessor的类，
                //就是上面那句话的意思

                //this.reader = new AnnotatedBeanDefinitionReader(this);
                //扫描器
                //this.scanner = new ClassPathBeanDefinitionScanner(this);
                context = new AnnotationConfigApplicationContext(UserModel.class);

        /*
        * 1、scan
        * 2、检查解析这个类 springBean的信息 ,BeanDefinition spring用来描述类的信息,
        * 实例出来的bean跟提供的没有直接关系，是由BeanDefinition描述的
         * 3、info存储 cache
        * 4、
        *
        * */
        System.out.println(context.getBean(A.class));
    }
}
