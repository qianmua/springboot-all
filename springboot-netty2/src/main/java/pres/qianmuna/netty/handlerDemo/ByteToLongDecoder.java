package pres.qianmuna.netty.handlerDemo;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/5/22
 * @time 22:45
 */
public class ByteToLongDecoder extends ByteToMessageDecoder {
    /**
     *
     * @param channelHandlerContext  上下文
     * @param byteBuf 入站msg
     * @param list list 将解码后的数据 传递给下一个handler
     * @throws Exception
     */
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {


        // long 占8个字节 ，只有够8个 才读取下一long
        if (byteBuf.readableBytes() >= 8){
            list.add(byteBuf.readLong());
        }
    }
}
