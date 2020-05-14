package pres.qianmuna.netty.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import pres.qianmuna.netty.handler.NettyClientHandler;

/**
 * Created by IntelliJ IDEA.
 *
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/5/14
 * @time 22:35
 */
public class NettyClient {


    public static void main(String[] args) throws InterruptedException {
        //客户端需要一个事件循环组
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();

        //创建健客户端启动对象
        Bootstrap bootstrap = new Bootstrap();

        try {
            //设置相关参数
            bootstrap
                    //线程组
                    .group(eventLoopGroup)
                    //客户端通道的实现类（反射）
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            //加入自己的处理器
                            socketChannel.pipeline().addLast(new NettyClientHandler());
                        }
                    });
            System.out.println("client start initialized");

            //启动客户端连接服务端
            //通过异步防止阻塞  涉及到netty
            ChannelFuture sync = bootstrap.connect("127.0.0.1", 6668).sync();
            //给关闭通道进行监听 异步模型
            sync.channel().closeFuture().sync();
        } finally {
            //优雅的关闭哦
            eventLoopGroup.shutdownGracefully();
        }


    }
}
