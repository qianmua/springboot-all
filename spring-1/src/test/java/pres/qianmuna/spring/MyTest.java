package pres.qianmuna.spring;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pres.qianmuna.spring.bea1.Student;
import pres.qianmuna.spring.service.SomeService;
import pres.qianmuna.spring.service.impl.SomServiceImpl;

/**
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/6/3  18:22
 * @description :
 */
public class MyTest {

    @Test
    public void m1(){

        // 一般方式
        SomeService service = new SomServiceImpl();

    }

    @Test
    public void m2(){

        /*
        * spring 创建 对象
        * */
        String config = "applicationContext.xml";
        // 创建 spring上下文 对象
        // 表示 spring 容器 的 对象

        /*
        * 在 spring 容器 加载 时
        * 创建 spring 的bean 对象
        * // 初始化
        * */
        ApplicationContext context = new ClassPathXmlApplicationContext(config);

        // 得到 bean 对象
        SomeService service = (SomeService) context.getBean("somService");
        service.say();

        // 调用的 set 方法 给属性 的值
        // 注入 值
        Student student = (Student) context.getBean("student");
        System.out.println(student);

    }

}
