package pres.qm.netty.channel;

import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;


public class FileLock02Demo {
    public static void main(String[] args) throws Exception {
        RandomAccessFile raf = new RandomAccessFile("E:\\workplace\\netty\\temp\\c.txt", "rw");
        FileChannel fileChannel = raf.getChannel();

        System.out.println("02 start");
        fileChannel.lock(1, 2, false);
        System.out.println("02 end");

        Thread.sleep(Integer.MAX_VALUE);
        fileChannel.close();
        raf.close();
    }
}
