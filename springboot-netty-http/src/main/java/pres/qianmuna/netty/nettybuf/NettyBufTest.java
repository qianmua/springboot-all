package pres.qianmuna.netty.nettybuf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.util.CharsetUtil;

import java.nio.charset.Charset;

/**
 * Created by IntelliJ IDEA.
 *
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/5/18
 * @time 22:11
 */
public class NettyBufTest {

    public static void main(String[] args) {

        //创建一个对象
        //是一个数组 byte[10]
        //不用像bufferByte 一样 读写 还要反转flip
        //底层维护了readIndex 和writerIndex
        // 两个指针 ，指向了 下一个数据
        ByteBuf buffer = Unpooled.buffer(10);
        for (int i = 0; i < 10; i++) {
            buffer.writeByte(i);
        }
        // 输出
        for (int i = 0; i < buffer.capacity(); i++) {
            System.out.println(buffer.readByte());
        }


        //创建buffer
        ByteBuf byteBuf = Unpooled.copiedBuffer("hello world得到", Charset.forName("utf-8"));

        // 判断有无value
        if (byteBuf.hasArray()){
            //转回去
            byte[] array = byteBuf.array();
            System.out.println(new String(array , CharsetUtil.UTF_8).trim());

            System.out.println(byteBuf);
            System.out.println(byteBuf.writerIndex());
            // 可读数量
            System.out.println(byteBuf.readableBytes());
            for (int i = 0; i < byteBuf.capacity(); i++) {
            }
        }
    }
}
