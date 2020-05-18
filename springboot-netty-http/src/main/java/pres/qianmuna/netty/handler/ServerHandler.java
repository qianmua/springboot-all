package pres.qianmuna.netty.handler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

import java.net.URI;

/**
 * Created by IntelliJ IDEA.
 *
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/5/17
 * @time 21:38
 */

/**
 * SimpleChannelInboundHandler extends ChannelInboundHandlerAdapter
 * HttpObject 相互通信的数据被封装成HttpObject
 */
public class ServerHandler extends SimpleChannelInboundHandler<HttpObject> {

    /**
     * 读取事件触发函数
     * 读取客户端数据
     * @param channelHandlerContext
     * @param httpObject
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, HttpObject httpObject) throws Exception {

        //判断请求
        if (httpObject instanceof HttpRequest){
            System.out.println(" msg type :" + httpObject.getClass());
            System.out.println(" client address :" + channelHandlerContext.channel().remoteAddress());

            //过滤请求
            HttpRequest request = (HttpRequest) httpObject;
            //得到uri
            URI uri = new URI(request.uri());
            //过滤
            if ("/favicon.ico".equals(uri.getPath())){
                System.out.println( " 请求 资源favicon ， 过滤 -> ");
                return ;
            }
            //回复信息给浏览器 【http协议】
            ByteBuf byteBuf = Unpooled.copiedBuffer("hello this is server. 中文编码", CharsetUtil.UTF_8);

            //构造http 响应
            FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, byteBuf);
            //其他信息
            //响应头
            response.headers().set(HttpHeaderNames.CONTENT_TYPE , "text/plain");
            // 头长度
            response.headers().set(HttpHeaderNames.CONTENT_LENGTH , byteBuf.readableBytes());

            //返回构建的
            channelHandlerContext.writeAndFlush(response);

        }
    }

    /**
     * 通道就绪事件
     * 通道活动
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
    }

    /**
     * 通道非活动
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
    }

    /**
     * 通道读取事件
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        super.channelRead(ctx, msg);
    }

    /**
     * 通道读取完成事件
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        super.channelReadComplete(ctx);
    }

    /**
     * 通道异常事件
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }


}
