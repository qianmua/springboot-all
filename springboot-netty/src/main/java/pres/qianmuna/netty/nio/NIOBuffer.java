package pres.qianmuna.netty.nio;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by IntelliJ IDEA.
 *
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/5/9
 * @time 19:02
 */
public class NIOBuffer {

    public static void main(String[] args) {


        //创建buffer
        ByteBuffer buffer = ByteBuffer.allocate(64);

        //放入数据
        buffer.putInt(11);
        buffer.putFloat(11.1F);
        buffer.putChar('a');
        buffer.putLong(100L);
        buffer.putShort( (short)11 );

        //转换
        buffer.flip();

        //取出 // 取出也要按顺序 否则可能异常 BufferUnderFlowException.....
        System.out.println(buffer.getInt());

        //得到一个只能读 的buffer
        //class type heap...R
        FloatBuffer buffer1 = buffer.asFloatBuffer();
    }

    public void mapBuffer() throws IOException {

        /**
         * 可以让文件在内存中修改并不需要拷贝一份
         * */

        RandomAccessFile accessFile = new RandomAccessFile("1.txt", "rw");

        //得到通道
        FileChannel channel = accessFile.getChannel();

        //extends ByteBuffer
        //p 光标起始 size 结束
        //相当于把这段加载到内存中
        // class type DirectByteBuffer
        MappedByteBuffer map = channel.map(FileChannel.MapMode.READ_WRITE, 0, 100);

        map.put(1,(byte)'王');

        System.out.println("ending...");

        //close
        channel.close();
        accessFile.close();
    }


}
