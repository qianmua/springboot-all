package pers.qianmuna.netty.group;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * Created by IntelliJ IDEA.
 *
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/5/20
 * @time 21:19
 */
public class ChatServer {

    //监听端口
    private int port;

    public ChatServer(int port){
        this.port = port;
    }

    /**
     * 处理客户端请求
     */
    public void run() throws InterruptedException {

        // 线程组
        EventLoopGroup boss = new NioEventLoopGroup(1);
        EventLoopGroup work = new NioEventLoopGroup(8);

        ServerBootstrap bootstrap = new ServerBootstrap();

        try {
            bootstrap.group(boss , work)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG,128)
                    .childOption(ChannelOption.SO_KEEPALIVE,true)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            //得到pipeline
                            ChannelPipeline pipeline = socketChannel.pipeline();

                            //pipeline 添加解码器
                            pipeline.addLast("decoder" , new StringDecoder());
                            // 添加编码器
                            pipeline.addLast("encoder" , new StringEncoder());
                            // 添加自己的业务处理
                            pipeline.addLast(new ServerHandler());

                        }
                    });

            System.out.println("server start ");
            ChannelFuture future = bootstrap.bind(port).sync();

            // 监听关闭
            ChannelFuture sync = future.channel().closeFuture().sync();
        } finally {
            work.shutdownGracefully();
            boss.shutdownGracefully();
        }


    }

    public static void main(String[] args) throws InterruptedException {
        new ChatServer(7000).run();
    }
}
