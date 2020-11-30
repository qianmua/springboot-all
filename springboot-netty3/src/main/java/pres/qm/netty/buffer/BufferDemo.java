package pres.qm.netty.buffer;

import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.util.concurrent.TimeUnit;


public class BufferDemo {

    public static void main(String[] args)  throws Exception {
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(Integer.MAX_VALUE);
        byte[] bytes =  new byte[]{1};
        for (int index = 0; index < Integer.MAX_VALUE; index ++) {
            byteBuffer.put(bytes);
        }

        TimeUnit.SECONDS.sleep(10);

        // 手动回收direct内存
        Method cleaner = byteBuffer.getClass().getMethod("cleaner");
        cleaner.setAccessible(true);
        Object clean = cleaner.invoke(byteBuffer);

        Method cleanMethod = clean.getClass().getMethod("clean");
        cleanMethod.setAccessible(true);
        cleanMethod.invoke(clean);

    }
}
