package pres.qianmuna.ioc.v2.framework.aspect;

/**
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/7/17  15:07
 * @description :
 */
public class LogAspect {

    public void before(){
        System.out.println("before");
    }

    public void after(){
        System.out.println("after");
    }

    public void afterThrowing(){
        System.err.println("err");
    }
}
