package pres.qianmuna.netty.codec.handler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

import java.util.Random;

/**
 * Created by IntelliJ IDEA.
 *
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/5/14
 * @time 22:44
 */
public class NettyClientHandler extends ChannelInboundHandlerAdapter {

    /**
     * 当通道就绪触发这个方法
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("client: \t" +ctx);
        //发送消息
        //Unpooled 非池化
        ctx.writeAndFlush(Unpooled.copiedBuffer("hello server喵喵" , CharsetUtil.UTF_8));


        //发送一个Student对象到服务器
//        StudentPoJO.Student.newBuilder().setId(10).setName("gogog0豹子头").build();

        //随机发送一个对象
        int i = new Random().nextInt();
        /*MessagePOJO.AllMessage message = null;
        switch (i) {
            case 0:
                message = MessagePOJO.AllMessage.newBuilder()
                        .setDataType(MessagePOJO.AllMessage.DataType.studentType)
                        .setStudent(MessagePOJO.Student.newBuilder().setId(5).setName("哈哈哈哈").build())
                        .build();
                break;
            case 1:
                message = MessagePOJO.AllMessage.newBuilder()
                        .setDataType(MessagePOJO.AllMessage.DataType.workType)
                        .setWorker(MessagePOJO.Worker.newBuilder().setId(100).setName("66666").setAddress("无").build())
                        .build();
                break;
            default:
                System.out.println("error");

        }

        ctx.writeAndFlush(message);*/

    }

    /**
     * 当通道有读取事件时触发
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        ByteBuf byteBuf = (ByteBuf)msg;
        System.out.println("服务器回复：\t"+byteBuf.toString(CharsetUtil.UTF_8));
        System.out.println("服务器地址：\t"+ctx.channel().remoteAddress());

    }

    /**
     * 异常处理
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        //异常信息
        System.out.println(cause.getMessage());

        ctx.close();
    }
}
