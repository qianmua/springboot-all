package pres.qianmuna.netty.tcp;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

import java.util.Arrays;

/**
 * Created by IntelliJ IDEA.
 *
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/5/24
 * @time 18:01
 */
public class ClientHandler extends SimpleChannelInboundHandler<ByteBuf> {

    private int count = 0;
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf) throws Exception {
        byte[] buffer = new byte[byteBuf.readableBytes()];

        byteBuf.readBytes(buffer);


        // 出现粘包现象
        System.out.println(" add ->     " + Arrays.toString(buffer));
        System.out.println(" count ->     " + ++ this.count);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        // 客户端 发送10条数据
        for (int i = 0; i < 10; i++) {
            ByteBuf byteBuf = Unpooled.copiedBuffer("hello Service" + i, CharsetUtil.UTF_8);
            ctx.writeAndFlush(byteBuf);
        }
    }
}
