package pres.qianmuna.spring.bean;

import pres.qianmuna.spring.aspect.TestAnnotatin;

/**
 * Created by IntelliJ IDEA.
 *
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/5/8
 * @time 21:23
 */
public class D {

    @TestAnnotatin( "动态代理")
    public void f(){
        System.out.println("6");
    }
}
