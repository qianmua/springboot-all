package pres.qianmuna.netty.handler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelPipeline;
import io.netty.util.CharsetUtil;

import java.nio.ByteBuffer;
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

        /*
        * 定时任务
        * */
        //处理耗时，大量数据据请求 会导致服务端阻塞
        // 所以需要异步去执行

        /*
        * 异步模型
        * netty 中的IO操作是异步的
        * 每个异步结果不会直接返回结果 而是直接返回一个ChannelFuture
        * 通过 future listener 机制 用户主动或者通过通知得到操作结果
        *
        * 即 在调用一个fun 的时候先返回一个 future 后面通过 future监控fun的处理
        *
        * future 表示异步的结果 可以检测执行是否成功
        *
        * 链式操作
        *
        * 读取 -> 处理（编码） -> 传输 -> 处理（解码） -》 操作
        * 每步都对应一个handler
        * 在handler处理中 可以使用 callback 或者 future 异步处理
        *
        * 当future 刚创建时 处于非完全机制  可以通过返回的 ChannelFuture 得到操作状态，注册监听函数执行完成后操作
        *
        * 状态：
        * idDone 完成？
        * isSuccess 成功？
        * getCause 失败原因
        * isChanneled 是否取消
        * addListener 注册监听器
        *
        *
        *
        *
        * */
        /*
        * 解决
        * */

        //自定义普通任务
        /**
         * 会被提交到NIOEventLoop中的taskQueue中去执行
         * 是个队列 所以要取一个去执行
         * 在同一个线程哦
         */
        ctx.channel().eventLoop().execute( () ->{
            try {
                Thread.sleep(3 * 1000);
                ctx.writeAndFlush(Unpooled.copiedBuffer("hello 喵喵喵 自定义普通任务" , CharsetUtil.UTF_8));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        /*
        * 自定义定时任务
        * 是提交到scheduleTaskQueue中执行的
        * */
        ctx.channel().eventLoop().schedule(() ->{
            try {
                TimeUnit.SECONDS.sleep(2);
                ctx.writeAndFlush(Unpooled.copiedBuffer("喵喵喵 ， 定时普通任务", CharsetUtil.UTF_8) );
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },2 , TimeUnit.SECONDS);



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
