package pers.qianmuna.netty.heart;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

/**
 * Created by IntelliJ IDEA.
 *
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/5/20
 * @time 22:09
 */
public class HeartServer {


    public static void main(String[] args) {
        EventLoopGroup boss = new NioEventLoopGroup(1);
        EventLoopGroup work = new NioEventLoopGroup(8);


        try {
            ServerBootstrap bootstrap = new ServerBootstrap();

            bootstrap.group(boss,work)
                    .channel(NioServerSocketChannel.class)
                    //在boss增加日志处理
                    .handler( new LoggingHandler(LogLevel.INFO))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            ChannelPipeline pipeline = socketChannel.pipeline();

                            //加入netty 提供的 handler
                            //IdleStateHandler 时netty提供的处理空闲状态的handler
                            /**
                             * 参数
                             * 1、 表示多长时间没有读 就会发送一个心跳检测 是否连接
                             * 2、 表示多长时间没有写 就会发送一个心跳检测 是否连接
                             * 3、 表示多长时间没有读写 就会发送一个心跳检测 是否连接
                             *
                             * 当channel没有read，write 或者两者 触发一个事件
                             *
                             * 通过心跳包 准确的检测 client 是否活跃
                             *
                             * 触发后 会传递给下一个handler 的 userEventTriggered 处理
                             *
                             * pipeline 是双向链表记得吗
                             *
                              */
                            pipeline.addLast(new IdleStateHandler(3,5,7, TimeUnit.SECONDS));

                            //对空闲处理
                            pipeline.addLast(new ServerHandler());
                        }
                    });

            ChannelFuture sync = bootstrap.bind(7000).sync();

            sync.channel().closeFuture().sync();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            boss.shutdownGracefully();
            work.shutdownGracefully();
        }
    }
}
