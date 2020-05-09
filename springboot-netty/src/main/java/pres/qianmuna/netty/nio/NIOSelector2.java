package pres.qianmuna.netty.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 *
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/5/9
 * @time 21:02
 */
public class NIOSelector2 {

    /**
     * 前面看不懂，不对  听不懂 后面差不多就明白了
     * */
    public static void main(String[] args) throws IOException {
        // 创建 ServerSocketChannel 通道
        ServerSocketChannel open = ServerSocketChannel.open();

        //得到selector
        Selector selector = Selector.open();

        //绑定端口 在服务端监听
        open.socket().bind(new InetSocketAddress(9090));

        //设置为非阻塞式
        open.configureBlocking(false);

        //注册 到Selector中西 添加事件
        open.register(selector , SelectionKey.OP_ACCEPT);

        //等待连接
        while (true){

            //监听一秒
            if( selector.select(1000) == 0){
                System.out.println("无连接");
                continue;
            }

            //通过set反向得到通道
            Set<SelectionKey> selectionKeys = selector.selectedKeys();

            //使用迭代器迭代
            Iterator<SelectionKey> iterator = selectionKeys.iterator();

            while (iterator.hasNext()){
                //get
                SelectionKey selectionKey = iterator.next();

                //可能是连接的事件 ， 或者 读的事件

                //根据不同的事件处理
                //OP_ACCEPT事件 ， 有客户端连接
                if (selectionKey.isAcceptable()){
                    System.out.println("has a link..." + open.hashCode());

                    //accept 这个方法是阻塞的， 但是！ 重点来了
                    // 我们这里是事件驱动过来的，所以直接能连接到！
                    //懂了吗？ 一半
                    SocketChannel accept = open.accept();
                    //设置非阻塞
                    accept.configureBlocking(false);


                    //注册到 selector
                    //并且给SocketChannel 关联一个buffer
                    accept.register(selector, SelectionKey.OP_READ , ByteBuffer.allocate(1024));

                }

                //OP_READ事件
                if (selectionKey.isReadable()){

                    // 通过key反向得到channel
                    SocketChannel channel = (SocketChannel) selectionKey.channel();
                    //得到该channel的buffer
                    ByteBuffer buffer = (ByteBuffer) selectionKey.attachment();
                    //Channel 读取 缓冲区中data
                    channel.read(buffer);

                    //处理业务
                    System.out.println(" 得到数据 : " + new String(buffer.array()));

                }

                //移除当前key 防止重复操作
                iterator.remove();

            }





        }

    }
}
