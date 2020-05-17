package pres.qianmuna.netty.http;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import pres.qianmuna.netty.initialitializer.ServerInitializer;

/**
 * Created by IntelliJ IDEA.
 *
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/5/17
 * @time 21:38
 */
public class HttpServer {

    /*
    * 服务端
    *
    * 浏览器访问一次发送两次请求哦
    *
    * 一次请求地址：
    * 第二次请求网页图标
    *
    * 过滤请求处理
    *
    * 无状态~！
    * */

    public static void main(String[] args) throws InterruptedException {
        EventLoopGroup boss = new NioEventLoopGroup();
        EventLoopGroup work = new NioEventLoopGroup();

        try {
            ServerBootstrap bootstrap = new ServerBootstrap();

            bootstrap.group(boss,work)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ServerInitializer());
            /*
            * 注意端口绑定问题
            * */
            ChannelFuture sync = bootstrap.bind(8989).sync();


            sync.channel().closeFuture().sync();

        } finally {
            boss.shutdownGracefully();
            work.shutdownGracefully();
        }

    }

}
