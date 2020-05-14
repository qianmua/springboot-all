package pres.qianmuna.netty.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import pres.qianmuna.netty.handler.NettyServiceHandler;

/**
 * Created by IntelliJ IDEA.
 *
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/5/13
 * @time 23:04
 */
public class NettyServer {


    public static void main(String[] args) throws InterruptedException {
        // 创建BOssGroup and WorkGroup 线程组
        //boss 处理连接
        // work 处理业务
        //含有的子线程（NioEventLoop）个数默认时cpu核数*2
        EventLoopGroup boss = new NioEventLoopGroup();
        EventLoopGroup work = new NioEventLoopGroup();
        try {

            //创建启动对象，配置参数
            ServerBootstrap bootstrap = new ServerBootstrap();
            // 绑定线程组
            bootstrap.group(boss , work)
                    // 服务器通道实现 boss 线程 用NioServerSocketChannel
                    .channel(NioServerSocketChannel.class)
                    // 线程队列连接个数
                    .option(ChannelOption.SO_BACKLOG,128)
                    //设置保持活动连接状态
                    .childOption(ChannelOption.SO_KEEPALIVE,true)
                    .childHandler(new ChannelInitializer<SocketChannel>(){
                        //创建一个通道测试对象
                        //给pipeline设置处理器
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {

                            // 给pipeline 添加handle
                            socketChannel.pipeline().addLast(new NettyServiceHandler());
                        }
                    });
            //给work对应的EventLoop设置对应的管道

            System.out.println("server reader....");

            //绑定端口同步
            //启动服务
            ChannelFuture future = bootstrap.bind(6668).sync();

            //监听关闭通道
            future.channel().closeFuture().sync();
        } finally {
            //优雅的关闭；
            boss.shutdownGracefully();
            work.shutdownGracefully();
        }

    }
}
