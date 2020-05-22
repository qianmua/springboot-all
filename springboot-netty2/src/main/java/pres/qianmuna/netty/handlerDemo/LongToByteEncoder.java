package pres.qianmuna.netty.handlerDemo;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * Created by IntelliJ IDEA.
 *
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/5/22
 * @time 22:54
 */
public class LongToByteEncoder extends MessageToByteEncoder<Long> {

    /**
     * 编码
     * @param channelHandlerContext
     * @param aLong
     * @param byteBuf
     * @throws Exception
     */
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Long aLong, ByteBuf byteBuf) throws Exception {

        System.out.println("encoder ltb");

        System.out.println("msg -> :\t\t" + aLong);

        byteBuf.writeLong(aLong);

    }
}
