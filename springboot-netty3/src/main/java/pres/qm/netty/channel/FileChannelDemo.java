package pres.qm.netty.channel;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;


public class FileChannelDemo {
    public static void main(String[] args) throws Exception {
//        test();
        truncateTest();
    }

    private static void truncateTest() throws Exception {
        FileOutputStream fos = new FileOutputStream(new File("E:\\workplace\\netty\\temp\\b.txt"));
        ByteBuffer byteBuffer = ByteBuffer.wrap("abcde".getBytes());
        FileChannel channel = fos.getChannel();
        channel.write(byteBuffer);
        channel.position(2);
        channel.truncate(2);
        channel.close();
        fos.close();
    }

    public static void test() throws Exception {
        FileOutputStream fos = new FileOutputStream(new File("E:\\workplace\\netty\\temp\\a.txt"));
        FileChannel channel = fos.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.wrap("abcde".getBytes());
        System.out.println("position:" + channel.position());
        channel.write(byteBuffer);
        System.out.println("position:" + channel.position());
        byteBuffer.rewind();
        channel.write(byteBuffer, 2);
        System.out.println("position:" + channel.position());
        channel.close();
        fos.close();
    }
}
