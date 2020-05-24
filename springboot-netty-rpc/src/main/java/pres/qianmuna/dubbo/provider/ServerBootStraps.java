package pres.qianmuna.dubbo.provider;

/**
 * Created by IntelliJ IDEA.
 *
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/5/24
 * @time 20:25
 */
public class ServerBootStraps {

    public static void main(String[] args) {
        // 启动一个 服务提供者 nettyServer

        NettyServer.start("127.0.0.1" , 7000);


    }
}
