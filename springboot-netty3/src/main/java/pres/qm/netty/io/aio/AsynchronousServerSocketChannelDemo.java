package pres.qm.netty.io.aio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;


public class AsynchronousServerSocketChannelDemo {
    public static void main(String[] args) throws Exception {
        AsynchronousServerSocketChannel asynchronousServerSocketChannel = AsynchronousServerSocketChannel.open();
        asynchronousServerSocketChannel.bind(new InetSocketAddress("127.0.0.1", 9090));

        asynchronousServerSocketChannel.accept(null, new CompletionHandler<AsynchronousSocketChannel, Object>() {
            @Override
            public void completed(AsynchronousSocketChannel result, Object attachment) {
                // 读
                ByteBuffer byteBuffer = ByteBuffer.allocate(20);
                result.read(byteBuffer, null, new CompletionHandler<Integer, Object>() {
                    @Override
                    public void completed(Integer length, Object attachment) {
                        System.out.println(new String(byteBuffer.array(), 0, length));

                        // 写
                        result.write(ByteBuffer.wrap("server:123456".getBytes()), null, new CompletionHandler<Integer, Object>() {
                            @Override
                            public void completed(Integer result, Object attachment) {
                                System.out.println("write success!");
                                try {
                                    asynchronousServerSocketChannel.close();
                                } catch (Exception e) {
                                }
                            }

                            @Override
                            public void failed(Throwable exc, Object attachment) {
                                System.out.println("write fail!");
                            }
                        });
                    }

                    @Override
                    public void failed(Throwable exc, Object attachment) {
                        System.out.println("read fail!");
                    }
                });
            }

            @Override
            public void failed(Throwable exc, Object attachment) {
                System.out.println("accept fail!");
            }
        });
        while (asynchronousServerSocketChannel.isOpen()) {
        }
    }
}
