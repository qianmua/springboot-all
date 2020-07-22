package pres.qianmuna.rpc.server;

/**
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/7/22  16:58
 * @description : start
 */
public class ServerStart {

    public static void main(String[] args) {
        RpcServer server = new RpcServer();

        server.publish("pres.qianmuna.rpc.service");
        server.start();


    }
}
