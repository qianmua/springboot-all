package pres.qianmuna.aop;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import pres.qianmuna.aop.app.AppConfig;
import pres.qianmuna.aop.entity.UserBean;

/**
 * Created by IntelliJ IDEA.
 *
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/5/9
 * @time 13:08
 */
public class SpringAopDemoApplication {
    public static void main(String[] args) {

        /**
         * AOP 面向切面编程
         *
         * spring aop 是实现aop的一种手段
         * aspectJ 也是实现aop的东东
         *
         * spring aop 借助了 aspectJ的编程风格
         *
         *
         * */

        //构建初始化context
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        //得到bean对象
        System.out.println(context.getBean("userBean"));
        context.getBean(UserBean.class).say();

    }


}
