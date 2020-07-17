package pres.qianmuna.ioc.v2.framework.aop;

import pres.qianmuna.ioc.v2.framework.aop.aspect.Advice;
import pres.qianmuna.ioc.v2.framework.aop.support.AdvicedSupport;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Map;

/**
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/7/17  15:38
 * @description : JdkDunamicAopProxy
 */
public class JdkDynamicAopProxy implements InvocationHandler {

    private AdvicedSupport config;

    /**
     * 织入
     * @param config config
     */
    public JdkDynamicAopProxy(AdvicedSupport config) {
        this.config = config;
    }

    /**
     * 得到代理 对象
     * this
     * 回调 invoke
     * @return proxy
     */
    public Object getProxy() {
        return Proxy.newProxyInstance(this.getClass().getClassLoader() , this.config.getTargetClass().getInterfaces() ,this );
    }

    /**
     * 代理
     * 织入
     * @param proxy proxy
     * @param method method
     * @param args args
     * @return proxy
     * @throws Throwable err
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Map<String , Advice> advices = this.config.getAdvice(method,this.config.getTargetClass());

        // 前置
        invokeAdvice(advices.get("before"));

        Object invoke = null;

        try {
            // 调用
            invoke = method.invoke(this.config.getTarget(), args);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            invokeAdvice(advices.get("after-throwing"));
        }

        invokeAdvice(advices.get("after"));

        return invoke;
    }

    /**
     * 通知
     * @param advice
     */
    private void invokeAdvice(Advice advice) {
        try {
            advice.getAdviceMethod().invoke(advice.getAspect());
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

    }
}
