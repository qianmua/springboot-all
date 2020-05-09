package pres.qianmuna.spring.proxy;

import pres.qianmuna.spring.aspect.TestAnnotatin;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by IntelliJ IDEA.
 *
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/5/8
 * @time 21:52
 */
/**
 * java 动态代理机制
 * 代理逻辑
 * */
public class HelloHadler implements InvocationHandler {

    /**
     * 是java的动态代理哦！
     * 代理对象
     * @param proxy
     * @param method
     * @param args
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        System.out.println("-----------------------------------------------------");
//        System.out.println(method.getAnnotation(TestAnnotatin.class).value());
//        System.out.println(method.getAnnotation(TestAnnotatin.class) == null ? "null" : "not null");

        // 这里写 代理逻辑
        //是否Object属性
        if (Object.class.equals(method.getDeclaringClass())){
            //拿到代理类
            return method.invoke(this,args);
        }

//        System.out.println(method.getName());
        System.out.println("-----------------------------------------------------");

        return null;
    }
}
