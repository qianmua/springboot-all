package pres.qianmuna.spring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import pres.qianmuna.spring.bean.A;
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
        AnnotationConfigApplicationContext
                context = new AnnotationConfigApplicationContext(UserModel.class);

        System.out.println(context.getBean(A.class));
    }
}
