package pres.qianmuna.dubbo.consumer;

import pres.qianmuna.dubbo.publics.HelloInterface;

/**
 * Created by IntelliJ IDEA.
 *
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/5/24
 * @time 21:12
 */
public class ClientBootstrap {

    private static final String PROVIDER = "Hello#hello#";

    public static void main(String[] args) {

        //创建消费者
        NettyClient client = new NettyClient();

        //创建代理对象
        // 异常
        HelloInterface bean = (HelloInterface) client.getBean(HelloInterface.class, PROVIDER);

        //通过代理对象调用提供者方法
        String hello = bean.hello("hello dubbo RPC ~");

        System.out.println("结果是 hello ->>>>>>>>>>>>>  " + hello);


    }
}
