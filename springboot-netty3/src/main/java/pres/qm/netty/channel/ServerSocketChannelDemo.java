package pres.qm.netty.channel;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;


public class ServerSocketChannelDemo {
    public static void main(String[] args) throws Exception {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress("localhost", 8080));
        serverSocketChannel.configureBlocking(true);
        SocketChannel socketChannel = serverSocketChannel.accept();
        ByteBuffer byteBuffer = ByteBuffer.allocate(10);
        int length;
        while ((length = socketChannel.read(byteBuffer)) != -1) {
           System.out.println(new String(byteBuffer.array(), 0, length));
           byteBuffer.clear();
        }
        socketChannel.close();
        serverSocketChannel.close();
    }
}
