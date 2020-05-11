package pres.qianmuna.netty.chat;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;

/**
 * Created by IntelliJ IDEA.
 *
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/5/11
 * @time 21:45
 */
public class ChatServer {

    //属性
    private Selector selector;
    private ServerSocketChannel listenChannel;
    private static final int PORT = 7777;

    //init
    public ChatServer(){

        try {
            //得到选择器
            selector = Selector.open();
            //得到监听
            listenChannel = ServerSocketChannel.open();
            //绑定端口
            listenChannel.socket().bind(new InetSocketAddress(PORT));
            //设置非阻塞
            listenChannel.configureBlocking(false);
            //绑定注册到selector
            listenChannel.register(selector , SelectionKey.OP_ACCEPT);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 监听
     */
    public void listen(){

        try {
            while (true){
                //没有事件就阻塞 // 参数每个一段时间监听
                int i = selector.select();
                //有事件处理
                if (i > 0){
                    //得到事件响应通道key
                    Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                    //处理事件
                    while (iterator.hasNext()){
                        SelectionKey next = iterator.next();

                        //监听连接事件
                        if (next.isAcceptable()){
                            //连接事件
                            SocketChannel accept = listenChannel.accept();
                            accept.configureBlocking(false);
                            //注册到select
                            accept.register(selector , SelectionKey.OP_READ);
                            //提示
                            System.out.println(accept.getRemoteAddress() + "连接..");
                        }

                        if (next.isReadable()){
                            //read事件

                            readChannelData(next);
                        }

                        //remove 当前key
                        iterator.remove();
                    }
                }else
                    System.out.println("wait....");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 读取缓冲区数据
     * @param selectionKey
     */
    private void readChannelData(SelectionKey selectionKey){
        SocketChannel channel = null;
        try {
            //得到关联的channel
            channel = (SocketChannel) selectionKey.channel();
            channel.configureBlocking(false);
            //buffer
            ByteBuffer buffer = ByteBuffer.allocate(1024);

            int read = channel.read(buffer);
            //读到value
            if (read > 0){
                //将缓冲区数据cans
                String str = new String(buffer.array());
                System.out.println("来自 :: say - >" + str.trim());

                //转发消息 <排除自己>
                sendInfoToClient(str, channel);
            }
        } catch (IOException e) {
            try {
                System.out.println(channel.getRemoteAddress() + "off.....");
                //re注册
                selectionKey.cancel();
                //关闭通道
                channel.close();

                listenChannel.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

    }


    private void sendInfoToClient(String msg , SocketChannel self) throws IOException {

        System.out.println("-------转发------");
        //所有注册的channel 并且排除self
        for (SelectionKey key : selector.keys()) {

            Channel channel = key.channel();
            //排除自己
            if (channel instanceof SocketChannel &&  channel != self){

                SocketChannel socketChannel = (SocketChannel) channel;
                //msg w to buffer
                ByteBuffer buffer = ByteBuffer.wrap(msg.getBytes());
                //写入到channel
                socketChannel.write(buffer);
            }

        }
    }

    /**
     * start
     * @param args
     */
    public static void main(String[] args) {
        // 注册服务端
        ChatServer chatServer = new ChatServer();
        //监听
        chatServer.listen();
    }
}
