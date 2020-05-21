package pres.qianmuna.netty.codec.handler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelPipeline;
import io.netty.util.CharsetUtil;

import java.util.concurrent.TimeUnit;

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

        //读取从客户端发送的数据
//        StudentPOJO.Student student = (StudentPOJO.Student) msg;


        ///读取


        //read
        System.out.println("客户端发送的数据 -> id =" + 1/*student.getId()*/);




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
