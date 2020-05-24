package pres.qianmuna.netty.tcp;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import pres.qianmuna.netty.handlerDemo.ClientInitializer;

/**
 * Created by IntelliJ IDEA.
 *
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/5/24
 * @time 17:59
 */
public class TcpClient {



    public static void main(String[] args) throws InterruptedException {
        EventLoopGroup group = new NioEventLoopGroup();

        Bootstrap bootstrap = new Bootstrap();

        try {
            bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new ClientInit());

            ChannelFuture future = bootstrap.connect("localhost", 7000).sync();

            future.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully();
        }

    }

}
