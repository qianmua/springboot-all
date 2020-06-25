package pres.qianmuna.aop2.proxy;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/6/25  21:06
 * @description :
 */
public class MAdvisor implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        System.out.println("my invoker");
        // invocation 对目标方法 的引用

        //调用链 中 的 下一个
        Object proceed = invocation.proceed();

        System.out.println("after invoker");
        return proceed;
    }
}
