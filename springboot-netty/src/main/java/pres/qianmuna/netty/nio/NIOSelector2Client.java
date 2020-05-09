package pres.qianmuna.netty.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * Created by IntelliJ IDEA.
 *
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/5/9
 * @time 21:38
 */
public class NIOSelector2Client {


    /**
     * 客户端
     * */
    public static void main(String[] args) throws IOException {

        //创建通道
        SocketChannel open = SocketChannel.open();
        //设置非阻塞
        open.configureBlocking(false);
        //提供IP和端口
        InetSocketAddress localhost = new InetSocketAddress("localhost", 9090);

        //连接服务器
        if ( !open.connect(localhost)){

            while (!open.finishConnect()){
                System.out.println("连接中。。。无阻塞哦~");
            }
        }

        //link success
        String msg = "hello nio selector netty!";
        // wrap 将一个字节数组 生产生buffer
        ByteBuffer byteBuffer = ByteBuffer.wrap(msg.getBytes());

        //发送数据
        open.write(byteBuffer);

        //手动阻塞客户端
        System.in.read();

    }
}
