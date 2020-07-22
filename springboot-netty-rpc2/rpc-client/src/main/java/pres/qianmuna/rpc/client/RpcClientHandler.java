package pres.qianmuna.rpc.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.Getter;

/**
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/7/22  21:19
 * @description :
 */
public class RpcClientHandler extends SimpleChannelInboundHandler<Object> {

    @Getter
    private Object result;

    /**
     * 结果 处理
     * @param ctx ctx
     * @param msg obj
     * @throws Exception err
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {

        // 客户端 处理 服务端 结果
        // 接收 结果 类型
        result = msg;


    }
}
