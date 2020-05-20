package pers.qianmuna.netty.group;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.util.Scanner;

/**
 * Created by IntelliJ IDEA.
 *
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/5/20
 * @time 21:48
 */
public class ChatClient {

    private final String HOST ;

    private final int PORT;

    /**
     * 绑定主机和端口
     * @param HOST
     * @param PORT
     */
    public ChatClient(String HOST, int PORT) {
        this.HOST = HOST;
        this.PORT = PORT;
    }

    public void run() throws InterruptedException {
        NioEventLoopGroup group = new NioEventLoopGroup();

        try {
            Bootstrap bootstrap = new Bootstrap()
                    .group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {

                            ChannelPipeline pipeline = socketChannel.pipeline();

                            pipeline.addLast("decoder" , new StringDecoder());
                            pipeline.addLast("encoder" , new StringEncoder());

                            pipeline.addLast(new ChatHandler());
                        }
                    });

            ChannelFuture future = bootstrap.connect(HOST,PORT).sync();
            Channel channel = future.channel();
            System.out.println("----------" +channel.localAddress()+"----------");

            //客户端扫描输入
            Scanner scanner = new Scanner(System.in);
            while (scanner.hasNextLine()){
                String s = scanner.nextLine();
                //send
                channel.writeAndFlush(s + "\r\n");
            }
        } finally {

            group.shutdownGracefully();

        }

    }

    public static void main(String[] args) throws InterruptedException {
        new ChatClient("127.0.0.1" , 7000).run();
    }
}
