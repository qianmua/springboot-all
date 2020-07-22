package pres.qianmuna.rpc.consumer;

import pres.qianmuna.rpc.client.RpcProxy;
import pres.qianmuna.rpc.service.TestService;

/**
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/7/22  17:06
 * @description :
 */
public class RpcConsumer {

    public static void main(String[] args) {
        /*
        * 转换 对应的 方法 去 远程 调用
        * */
        TestService service = RpcProxy.create(TestService.class);
        System.out.println(service.sayHello("test"));
        System.out.println(service.hashCode());
    }
}
