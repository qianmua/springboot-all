package pres.qianmuna.netty.tcp;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

import java.util.Arrays;
import java.util.UUID;

/**
 * Created by IntelliJ IDEA.
 *
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/5/24
 * @time 18:05
 */
public class ServerHandler extends SimpleChannelInboundHandler<ByteBuf> {
    private int count = 0;

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf) throws Exception {
        byte[] bytes = new byte[byteBuf.readableBytes()];
        byteBuf.readBytes(bytes);
        //toString
        System.out.println("getmes" + Arrays.toString(bytes));
        System.out.println("num------------>" + ++this.count );

        //回送数据
        channelHandlerContext.writeAndFlush(Unpooled.copiedBuffer(UUID.randomUUID().toString() , CharsetUtil.UTF_8));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println(cause.getMessage());
        ctx.close();
    }
}
