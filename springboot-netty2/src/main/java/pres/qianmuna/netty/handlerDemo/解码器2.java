package pres.qianmuna.netty.handlerDemo;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/5/24
 * @time 17:38
 */
public class 解码器2 extends ReplayingDecoder<Void> {
    /**
     * 解码器2
     *
     * 不是所有的byteBuf 都支持 入药第哦啊用一个不被支持的方法就会抛出异常
     *
     * 在某些情况之下 可能会拉低速度
     *
     *
     * 例如： 网络慢 消息格式复杂
     * 消息就会被拆成碎片 速度变慢
     *
     *
     *
     * netty其他编码器
     *
     * LineBaseFrame 使用 \n 或者 、\r\n 控制
     * DelimitBased 使用自定义符号处理消息分割
     * HttpObject 通过指定长度表示消息
     *
     *
     * @param channelHandlerContext
     * @param byteBuf
     * @param list
     * @throws Exception
     */
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        System.out.println("解码器2");

        //ReplayingDecoder 不用判断数据 大小读取
        list.add(byteBuf);
    }
}
