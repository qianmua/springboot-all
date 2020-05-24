package pres.qianmuna.dubbo.provider;

import pres.qianmuna.dubbo.publics.HelloInterface;

/**
 * Created by IntelliJ IDEA.
 *
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/5/24
 * @time 20:23
 */
public class HelloServiceImpl implements HelloInterface {
    @Override
    public String hello(String msg) {
        System.out.println("get consumer -> " + msg);

        if ((msg != null)){
            return "hello client get your msg    _____>" + msg;
        }else {
            return " your msg is empty .";
        }
    }
}
