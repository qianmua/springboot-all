package pres.qianmuna.netty.handlerDemo;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

/**
 * Created by IntelliJ IDEA.
 *
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/5/22
 * @time 22:44
 */
public class ServerInitlalizer extends ChannelInitializer<SocketChannel> {


    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();

        //对入站 handler 解码

        pipeline.addLast(new ByteToLongDecoder());

        //handler
        pipeline.addLast(new SServerHandler());
    }
}
