package pres.qianmuna.dubbo.provider;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
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
 * @date 2020/5/24
 * @time 20:27
 */
public class NettyServer {

    public static void start(String hostName , int port){
        try {
            startServer0(hostName, port);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    private static void startServer0(String hostName , int port) throws InterruptedException {

        NioEventLoopGroup boss = new NioEventLoopGroup(1);
        NioEventLoopGroup work = new NioEventLoopGroup();


        try {
            ServerBootstrap bootstrap = new ServerBootstrap();

            bootstrap.group(boss , work)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();

                            pipeline.addLast(new StringDecoder());
                            pipeline.addLast(new StringEncoder());
                            // 自己的业务处理
                            pipeline.addLast(new NettyServerHandler());
                        }
                    });
            ChannelFuture future = bootstrap.bind(port).sync();
            /*start*/
            // 服务器 启动
            System.out.println("服务启动");
            future.channel().closeFuture().sync();
        } finally {
            boss.shutdownGracefully();
            work.shutdownGracefully();
        }


    }
}
