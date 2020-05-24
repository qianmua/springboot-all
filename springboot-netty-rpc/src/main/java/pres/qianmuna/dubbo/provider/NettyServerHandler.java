package pres.qianmuna.dubbo.provider;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * Created by IntelliJ IDEA.
 *
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/5/24
 * @time 20:32
 */
public class NettyServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        // 得到客户端发送的消息 ， 并且调用服务

        System.out.println("msg =" + msg);

        //客户端调服务API的时候 要定义一个协议
        //比如 ： 消息 必须 以 某个字符开头
        // 或者 满足某些 格式
        if (msg.toString().startsWith("Hello#hello#")){
            String hello = new HelloServiceImpl().hello(msg.toString().substring(msg.toString().lastIndexOf("#") + 1));

            ctx.writeAndFlush(hello);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
