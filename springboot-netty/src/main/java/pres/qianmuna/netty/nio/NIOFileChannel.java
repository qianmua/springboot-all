package pres.qianmuna.netty.nio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by IntelliJ IDEA.
 *
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/5/9
 * @time 18:31
 */
public class NIOFileChannel {

    public static void main(String[] args) throws IOException {
        // channel

        String str = "hello netty";
        //创建输入流
        FileOutputStream outputStream = new FileOutputStream("");
        //创建输出流
        FileInputStream inputStream = new FileInputStream("");


        //得到对应的channel
        //FileChannelImpl
        //getChannel = FileChannelImpl.open(fd, path, false, true, append, this);
        FileChannel channel = outputStream.getChannel();

        //input
        FileChannel channel1 = inputStream.getChannel();

        //创建一个缓冲区
         ByteBuffer buffer = ByteBuffer.allocate(1024);

         // file.length
        ByteBuffer buffer1 = ByteBuffer.allocate(111);

        //写入
        buffer.put(str.getBytes());


        // flip
        buffer.flip();

        //写入
        channel.write(buffer);


        //读出流
        int read = channel1.read(buffer1);

        //println
        System.out.println(new String(buffer1.array()));

        //关闭流
        channel.close();

        outputStream.close();

    }

    /**
     * 正如方法名， 文件拷贝
     */
    public void fileCopy() throws IOException {
        //创建输入输出流
        FileOutputStream outputStream = new FileOutputStream("");
        FileInputStream inputStream = new FileInputStream("");

        //得到通道
        FileChannel outputStreamChannel = outputStream.getChannel();
        FileChannel inputStreamChannel = inputStream.getChannel();

        //拷贝
        outputStreamChannel.transferFrom(inputStreamChannel, 0 , inputStreamChannel.size());

        //关闭相关流
        inputStreamChannel.close();
        outputStreamChannel.close();

        inputStream.close();
        outputStream.close();


    }
}
