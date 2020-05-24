package pres.qianmuna.netty.tcp;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import pres.qianmuna.netty.handlerDemo.ServerInitlalizer;

/**
 * Created by IntelliJ IDEA.
 *
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/5/24
 * @time 17:59
 */
public class TcpService {


    /*
    * 模拟 tcp 粘包 拆包现象
    * */
    public static void main(String[] args) throws InterruptedException {

        EventLoopGroup boss = new NioEventLoopGroup();
        EventLoopGroup work = new NioEventLoopGroup();
        ServerBootstrap bootstrap = new ServerBootstrap();

        try {
            bootstrap.group(boss,work)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ServiceInit());
            ChannelFuture sync = bootstrap.bind(7000).sync();
            sync.channel().closeFuture().sync();
        } finally {
            boss.shutdownGracefully();
            work.shutdownGracefully();
        }

    }
}
