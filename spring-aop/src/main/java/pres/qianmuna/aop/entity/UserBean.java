package pres.qianmuna.aop.entity;

import org.springframework.stereotype.Component;

/**
 * Created by IntelliJ IDEA.
 *
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/5/9
 * @time 13:20
 */
@Component
public class UserBean {

    public void say(){
        System.out.println("hello aop");
    }
}
