package pres.qianmuna.annotation;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by IntelliJ IDEA.
 *
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/5/18
 * @time 11:15
 */
public class AnnotationDemoApplication {

    public static void main(String[] args) {

        // xml方式
//        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("");
//        context.getBean("");

        // 注解方式
//        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("");
//        context.getBean("");
        //关闭容器 销毁bean
//        context.close();

        /*
        *
        * ioc 容器 默认都是 单例的
        *
        * 有个单例池
        *
        * */


    }
}
