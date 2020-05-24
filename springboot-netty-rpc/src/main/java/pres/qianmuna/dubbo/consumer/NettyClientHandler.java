package pres.qianmuna.dubbo.consumer;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.concurrent.Callable;

/**
 * Created by IntelliJ IDEA.
 *
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/5/24
 * @time 20:49
 */
public class NettyClientHandler extends ChannelInboundHandlerAdapter implements Callable {

    private ChannelHandlerContext context;
    private String result; //返回结果
    private String para; // 参数

    /**
     * 连接后调用
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        // 提升当前域
        context = ctx;
    }

    /**
     * get 数据
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public synchronized void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        result = msg.toString();
        //唤醒等待中的线程
        // 发送数据后会wait
        // 唤醒他
        //call 之后
        //synchronized 和 call 是同步的 所以 加了 synchronized
        notify();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }

    /**
     * 被代理对象调用
     * 发送数据给服务
     * 然后wait 等待被唤醒继续处理
     *
     *
     *
     * @return
     * @throws Exception
     */
    @Override
    public synchronized Object call() throws Exception {

        context.writeAndFlush(para);
        // 等待 得到结果之后 唤醒
        wait();
        return result;
    }


    public void setPara(String para) {
        this.para = para;
    }
}
