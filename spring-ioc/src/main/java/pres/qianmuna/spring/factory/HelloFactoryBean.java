package pres.qianmuna.spring.factory;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;
import pres.qianmuna.spring.SpringIocApplication;
import pres.qianmuna.spring.bean.D;
import pres.qianmuna.spring.bean.EProxy;
import pres.qianmuna.spring.proxy.HelloHadler;

import java.lang.reflect.Proxy;
import java.util.logging.Handler;

/**
 * Created by IntelliJ IDEA.
 *
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/5/8
 * @time 21:22
 */
//注释后通过spring提供的另一个方式注册  ？ 什么方式
//ImportBeanDefinitionRegistrar 这种方式 ，辣鸡这都不知道。。。
//@Component
public class HelloFactoryBean implements FactoryBean {

    /**
     * 工厂模式
     *
     *  bean factory
     *  1、db
     *  2、bean
     *  3、rigi 注册
     *
     *
     * factory bean
     * 1、自己注册一个
     * 2、自己也是个bean
     *
     *
     * */

    //在这里动态代理

    /**
     * factory bean 的特殊地方
     * */
    @Override
    public Object getObject() throws Exception {

        //通过反射拿到对象集
        Class[] classes = new Class[]{EProxy.class};
        //通过代理得到对象
        //你这个代理的要是个接口呐
        EProxy eProxy = (EProxy) Proxy.newProxyInstance(HelloFactoryBean.class.getClassLoader()
                //代理谁？哪个接口
                , classes
                // 代理逻辑
                , new HelloHadler());

        return eProxy;
//        return new D();
    }

    @Override
    public Class<?> getObjectType() {

        return EProxy.class;
    }
}
