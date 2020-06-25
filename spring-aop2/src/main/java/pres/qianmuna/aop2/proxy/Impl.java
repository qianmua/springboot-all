package pres.qianmuna.aop2.proxy;

import pres.qianmuna.aop2.inter.Service;

/**
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/6/25  21:04
 * @description :
 */
public class Impl implements Service {
    @Override
    public void say() {
        System.out.println("hello");
    }
}
