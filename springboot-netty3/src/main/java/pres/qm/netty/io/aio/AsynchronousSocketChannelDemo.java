package pres.qm.netty.io.aio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;


public class AsynchronousSocketChannelDemo {
    public static void main(String[] args) throws Exception {
        AsynchronousSocketChannel channel = AsynchronousSocketChannel.open();
        channel.connect(new InetSocketAddress("127.0.0.1", 9090), null, new CompletionHandler<Void, Object>() {
            @Override
            public void completed(Void result, Object attachment) {
                channel.write(ByteBuffer.wrap("client:123456".getBytes()), null, new CompletionHandler<Integer, Object>() {
                    @Override
                    public void completed(Integer result, Object attachment) {
                        ByteBuffer byteBuffer = ByteBuffer.allocate(20);
                        channel.read(byteBuffer, null, new CompletionHandler<Integer, Object>() {
                            @Override
                            public void completed(Integer length, Object attachment) {
                                System.out.println(new String(byteBuffer.array(), 0, length));
                                try {
                                    channel.close();
                                } catch (IOException e) {
                                }
                            }

                            @Override
                            public void failed(Throwable exc, Object attachment) {
                                System.out.println("read fail!");
                            }
                        });
                    }

                    @Override
                    public void failed(Throwable exc, Object attachment) {
                        System.out.println("write fail!");
                    }
                });
            }

            @Override
            public void failed(Throwable exc, Object attachment) {
                System.out.println("connect fail!");
            }
        });
        while (channel.isOpen()) {
        }
    }
}
