package pers.qianmuna.netty.heart;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleStateEvent;

/**
 * Created by IntelliJ IDEA.
 *
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/5/20
 * @time 22:20
 */
public class ServerHandler extends ChannelInboundHandlerAdapter {


    /**
     * 处理空闲事件
     * @param ctx
     * @param evt
     * @throws Exception
     */
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {

        if (evt instanceof IdleStateEvent){

            //向下转型
            IdleStateEvent evt1 = (IdleStateEvent)evt;
            String type = null;
            switch (evt1.state()){
                case READER_IDLE:
                    type = "read";
                    break;
                case WRITER_IDLE:
                    type = "writer";
                    break;
                case ALL_IDLE:
                    type = "all";
                    // 空闲事件过长
                    ctx.channel().close();
                    System.out.println("close channel....");
                    break;
            }
            System.out.println(ctx.channel().remoteAddress() + "\ttimeout...\t" + type);
            System.out.println("处理..");

            //
        }
    }
}
