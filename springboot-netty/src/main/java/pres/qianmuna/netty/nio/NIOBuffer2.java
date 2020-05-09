package pres.qianmuna.netty.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

/**
 * Created by IntelliJ IDEA.
 *
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/5/9
 * @time 19:54
 */
public class NIOBuffer2 {

    /**
     * buffer 的分散和聚集
     * */


    public static void main(String[] args) throws IOException {
        /**
         * Scattering 写将数据写入到buffer数组
         * Gathering  读将独居读入到buffer数组
         * */

        //创建server channel
        ServerSocketChannel open = ServerSocketChannel.open();
        InetSocketAddress inetSocketAddress = new InetSocketAddress(8989);

        //绑定端口 并启动
        open.socket().bind(inetSocketAddress);

        //创建buffer数组
        ByteBuffer[] byteBuffers = new ByteBuffer[2];

        byteBuffers[1] = ByteBuffer.allocate(2);
        byteBuffers[1] = ByteBuffer.allocate(3);

        //等待客户端连接
        SocketChannel accept = open.accept();

        int msgSize = 8;

        while (true){

            accept.read(byteBuffers);

            //buffer p and limit
            //Arrays.asList
            Arrays.stream(byteBuffers)
                    .map( m -> "p" + m.position() + " l" + m.limit())
                    .forEach(System.out::println);


            //后面读完了直接writer

        }

    }
}
