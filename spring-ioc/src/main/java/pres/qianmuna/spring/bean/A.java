package pres.qianmuna.spring.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by IntelliJ IDEA.
 *
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/5/2
 * @time 23:20
 */
@Component
public class A {

    public A() {
        System.out.println("A zhuce A- >");
    }


    @Autowired
    B b;


    @Override
    public String toString() {
        return "A";
    }
}
