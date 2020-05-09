package pres.qianmuna.netty.nio;

import java.nio.IntBuffer;

/**
 * Created by IntelliJ IDEA.
 *
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/5/9
 * @time 17:09
 */
public class NIOServer {
    /**
     * NIO 三大组件
     * 通道 channel
     * 选择器 selector
     * 缓冲器 buffer
     *
     * channel buffer 程序 不是 直接向通道发送，中间有个缓冲区， 通过buffer 实现非阻塞的机制
     * // 实现的线程不会阻塞
     *
     * 每个 channel 对应一个 buffer
     * 一个selector 对应一个 线程 多个 channel
     * selector 根据不同事件 在各个通道切换
     * 数据读写通过buffer NIO中buffer可读可写 用 flip切换
     * channel  是双向的
     *
     *
     * BIO / NIO
     * BIO 用流的方式处理 NIO 用块的方式 IO效率
     * 阻塞 非阻塞
     * BIO基于字节和字符流 NIO 基于通道和buffer用单线程就可以监听多个客户端操作
     *
     *
     * 缓冲区 buffer // 底层是用数组维护的
     *     private int mark = -1; // 标记
     *     private int position = 0; //位置
     *     private int limit; //终点
     *     private int capacity; //容量
     *
     *
     *
     * */

    // 线程管理selector  selector 管理多个channel
    public static void main(String[] args) {

        //buffer
        //创建一个buffer 大小是5 存放int类型
        IntBuffer buffer = IntBuffer.allocate(5);
        buffer.put(1);
        buffer.put(2);

        //从buffer读取
        //将buffer 读写转换
        buffer.flip();

        while (buffer.hasRemaining()){
            // get 里面维护了个指针
            System.out.println(buffer.get());
        }
    }
}
