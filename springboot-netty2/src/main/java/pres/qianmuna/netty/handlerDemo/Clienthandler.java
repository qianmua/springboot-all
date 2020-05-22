package pres.qianmuna.netty.handlerDemo;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * Created by IntelliJ IDEA.
 *
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/5/22
 * @time 22:53
 */
public class Clienthandler extends SimpleChannelInboundHandler<Long> {
    /**
     * 读
     * @param channelHandlerContext
     * @param aLong
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Long aLong) throws Exception {

    }

    //发送数据
    //当连接时
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("send................");
//        ctx.writeAndFlush(Unpooled.copiedBuffer());
        ctx.writeAndFlush(123456L); //发送一个long
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }
}
