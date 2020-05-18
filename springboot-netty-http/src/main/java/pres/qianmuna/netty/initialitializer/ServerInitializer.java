package pres.qianmuna.netty.initialitializer;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;
import pres.qianmuna.netty.handler.ServerHandler;

/**
 * Created by IntelliJ IDEA.
 *
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/5/17
 * @time 21:39
 */
public class ServerInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        //加入处理器

        //得到管道
        ChannelPipeline pipeline = socketChannel.pipeline();

        // 加入到编解码器 netty 提供的
        //HttpServerCodec netty 提供的http 编解码器 // 对传过来的数据 解吗
        pipeline.addLast("HttpServerCodec", new HttpServerCodec());

        //添加自定义 handler
        pipeline.addLast("HttpServerHandler" , new ServerHandler());

        /*
        *
        * pipeline
        *
        * addFirst 链家到链表 第一个
        *
        * addLast 添加到链表最后一个
        *
        *
        * */

    }
}
