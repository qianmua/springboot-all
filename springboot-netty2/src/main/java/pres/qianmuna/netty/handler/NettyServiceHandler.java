package pres.qianmuna.netty.handler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelPipeline;
import io.netty.util.CharsetUtil;

import java.nio.ByteBuffer;

/**
 * Created by IntelliJ IDEA.
 *
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/5/14
 * @time 22:21
 */
public class NettyServiceHandler extends ChannelInboundHandlerAdapter {
    /**
     * 自定i一一个handler 实现netty规定好的handler 自定义我们的业务
     * */

    /**
     * 读取客户端发送的时间
     * @param ctx 上下文对象 包含 管道pipeline 通道channel 地址
     * @param msg 消息
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        System.out.println("当前线程： \t" + Thread.currentThread().getName());

        //ctx 包含了all信息
        System.out.println("server context :\t" + ctx);

        //channel 和 pipeline 关系
        //pipeline 本质是一个双向链表
        // 这俩是对应的关系， 都可以互相得到对方
        Channel channel = ctx.channel();
        ChannelPipeline pipeline = ctx.pipeline();

        //buffer 是netty的buffer 不是nio的，性能比nio的好点
        ByteBuf buffer = (ByteBuf)msg;

        System.out.println("msg: \t"+ buffer.toString(CharsetUtil.UTF_8));
        System.out.println("address: \t" + channel.remoteAddress());

    }

    /**
     * 数据读取完毕
     * 将数据发送回去
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {

        //将数据写入缓冲，并且刷新
        //然后对数据进行编码
        ctx.writeAndFlush(Unpooled.copiedBuffer("hello client 喵喵", CharsetUtil.UTF_8));
    }

    /**
     * 处理异常
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        //关闭通道
        ctx.close();
    }
}
