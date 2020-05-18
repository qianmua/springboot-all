package pres.qianmuna.annotation.auto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;

import javax.annotation.Resource;

/**
 * Created by IntelliJ IDEA.
 *
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/5/18
 * @time 14:08
 */
public class AotoDemo {

    /*
    * 自动装配
    *
    * @Autowired 自动注入 required=true 默认必须要由初始得value，就是被ioc代理 false 就是不必要
    * 默认按照类型去ioc容器中找
    * 多个得话 用Qualifier区分
    * 首选Bean @Primary 要是有多个，默认就用被Primary注解得
    *
    * @Resource (jsr250) java 规范得注解 ， 默认按照组件名装配
    *
    * AutowiredAnnotationBeanPostProcessor 解析完成自动装配得功能 （BeanPostProcessor 是一个后置处理器哦）
    *
    *
    * @Autowired  可以标注在方法上注入 从ioc中拿到
    * 也可以通过有参构造器中 ， spring在启动时会调用有参构造创建注入对象
    * 也可以放在参数上，从容器中得到
    * 只有一个有参构造 可以省略Autowired
    *
    * @Bean 标注的方法 ，参数中的对象 也从容器中注入
    *
    * */
//    @Autowired  可以标注在方法上注入 从ioc中拿到
//    @Qualifier
//    @Primary
//    @Resource



    /*
    *
    *
    * aware  相当于 spring的事件回调 ， 可以调用spring 底层的一些东西
    * 比如容器，beanName，解析器了什么的
    *
    * xxxAware
    *
    *
    * */

}
