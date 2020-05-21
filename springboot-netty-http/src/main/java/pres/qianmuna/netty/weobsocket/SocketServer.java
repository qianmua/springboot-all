package pres.qianmuna.netty.weobsocket;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * Created by IntelliJ IDEA.
 *
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/5/20
 * @time 22:32
 */
public class SocketServer {


    public static void main(String[] args) {
        EventLoopGroup boss = new NioEventLoopGroup(1);
        EventLoopGroup work = new NioEventLoopGroup(8);


        try {
            ServerBootstrap bootstrap = new ServerBootstrap();

            bootstrap.group(boss, work)
                    .channel(NioServerSocketChannel.class)
                    //在boss增加日志处理
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            ChannelPipeline pipeline = socketChannel.pipeline();

                            /*
                            * 数据在网络中传输的是 二进制码
                            *
                            * 所以需要编解码
                            *
                            * 客户端 数据 -》 编码 -》 网络传输（二进制字节码）
                            *
                            * 服务端 得到二进制数据 -》 解码 -》 数据
                            *
                            * netty提供的编码器
                            * StringEncoder 对字符串
                            * ObjectEncoder 对java对象编码
                            *
                            * 问题：
                            * netty 底层使用的是还是java序列化，效率不高
                            * 无法跨程序
                            * 序列化体积大
                            * 性能低
                            *
                            * 解决方案
                            * google的Protobuf
                            *
                            * Protobuf
                            * 可以用来序列化，适合做数据存储或者RPC数据交换
                            * 是以message方式管理数据
                            *
                            * 支持跨平台 跨语言
                            *
                            * 高性能高可靠
                            *
                            * 使用示意图
                            * 数据.protoc -> protoc.exe -> 数据.java -> 编码protobuf  -> 传输
                            * protobuf解码 -> 使用
                            *
                            *
                            *
                            * */

                            //基于http协议  使用http 编解码器
                            pipeline.addLast(new HttpServerCodec());
                            //使用块方式 写 添加ChunkedWriter
                            pipeline.addLast(new ChunkedWriteHandler());

                            //http 传输时分段的 可以将多个段 聚合
                            // 就是当浏览器发送大量数据时 就会发送多次 请求
                            // 使用 HttpObjectAggregator 将段 聚合起来
                            pipeline.addLast(new HttpObjectAggregator(8111));

                            /**
                             * websocket 数据是以帧（frame） 传递
                             * 6子类
                             * 浏览器请求时 ws://localhost:port/url
                             * ws 协议
                             * WebSocketServerProtocolHandler 将http协议升级为 ws协议 保持长连接
                             *
                             * 参数path 就是 接口了 ， 可以试别
                             *
                             * http协议升级为 ws 协议？
                             *
                             * 通过一个状态码 101 转换
                             * 将协议提升为ws协议
                             *
                             *
                             */
                            pipeline.addLast(new WebSocketServerProtocolHandler("/test"));


                            //处理业务
                            pipeline.addLast(new WebSocketServerHandler());
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
