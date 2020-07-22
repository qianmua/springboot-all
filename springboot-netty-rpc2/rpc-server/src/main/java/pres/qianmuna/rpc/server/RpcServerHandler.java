package pres.qianmuna.rpc.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import pres.qianmuna.rpc.dto.Invocation;

import java.util.Map;

/**
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/7/22  21:04
 * @description :
 */
public class RpcServerHandler extends SimpleChannelInboundHandler<Invocation> {

    private Map<String , Object> register;

    public RpcServerHandler(Map<String, Object> register) {
        this.register = register;
    }

    /**
     * 处理
     * @param ctx ChannelHandlerContext
     * @param msg Invocation 调用信息
     * @throws Exception err 异常
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Invocation msg) throws Exception {
        Object invoke = "nothing";
        //Invocation // 调用 信息 api
        //调用信息 得到 调用 接口
        // 在注册表里面 找到 服务
        // 然后 调用
        // ok 啦~
        // this is RPC
        if (register.containsKey(msg.getClassName())){
            Object provider = register.get(msg.getClassName());
            invoke = provider.getClass()
                    .getMethod(msg.getMethodName(), msg.getParamType())
                    .invoke(provider, msg.getParamValues());

        }

        // send client
        ctx.writeAndFlush(invoke);
        ctx.close();

    }


    /**
     *
     * @param ctx ctx
     * @param cause err
     * @throws Exception err
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {

        cause.printStackTrace();
        ctx.close();

    }
}
