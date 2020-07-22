package pres.qianmuna.rpc.service.impl;

import pres.qianmuna.rpc.service.TestService;

/**
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/7/22  21:37
 * @description :
 */
public class TestSericeImpl implements TestService {
    @Override
    public String sayHello(String hello) {
        return hello + "  i'm service hello world.";
    }
}
