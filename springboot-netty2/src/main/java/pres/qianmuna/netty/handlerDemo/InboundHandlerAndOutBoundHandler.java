package pres.qianmuna.netty.handlerDemo;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * Created by IntelliJ IDEA.
 *
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/5/22
 * @time 22:40
 */
public class InboundHandlerAndOutBoundHandler {


    /*
    * TCP 粘包 拆包
    *
    * tcp 是面向连接 面向流的收发数据 两端 要有一对一的socket 、
    * 为了处理 发送数据效率的问题 将间隔小，数据量小的数据封成一个大包 提高数据效率
    * 问题： 接收端 不容易分辨出真正的数据包
    *
    * 面向流的通信 是 无消息保护边界的
    *
    * 就出现了 粘包 拆包问题
    *
    * 情况：
    * 一次 读到了2个数据包 ， 这俩粘在一起了 出现粘包情况
    * 将一个 完整的包拆开了 和别的包粘在一区 ，出现拆包问题 ， 接受端 根部就不知道 他接受的 数据 是不是 完整的
    *
    *
    *
    *
    *
    *
    *
    *
    * */

    public static void main(String[] args) throws InterruptedException {

        EventLoopGroup boss = new NioEventLoopGroup(1);
        EventLoopGroup work = new NioEventLoopGroup(8);

        ServerBootstrap bootstrap = new ServerBootstrap();

        try {
            bootstrap.group(boss,work)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ServerInitlalizer());


            ChannelFuture sync = bootstrap.bind(7000).sync();

            sync.channel().closeFuture().sync();
        } finally {
            boss.shutdownGracefully();
            work.shutdownGracefully();
        }

    }
}
