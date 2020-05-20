package pres.qianmuna.netty.weobsocket;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.EventExecutorGroup;

import java.time.LocalDateTime;

/**
 * Created by IntelliJ IDEA.
 *
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/5/20
 * @time 22:41
 */
public class WebSocketServerHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    //TextWebSocketFrame 表示一个文本帧


    /**
     * 处理业务了
     * @param channelHandlerContext
     * @param textWebSocketFrame
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, TextWebSocketFrame textWebSocketFrame) throws Exception {
        System.out.println("get msg -> \t" + textWebSocketFrame.text());

        //回复消息
        channelHandlerContext.channel().writeAndFlush(new TextWebSocketFrame("server time:\t" + LocalDateTime.now()));

    }

    /**
     * link
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        //ctx.channel().id().asLongText() 表示唯一的值
        //asShotText 不是唯一的
        System.out.println("added use -> \t" + ctx.channel().id().asLongText());
    }

    /**
     * remove
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        System.out.println("remove use -> \t" + ctx.channel().id().asLongText());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("err -> :");
        System.out.println(cause.getMessage());
    }
}
