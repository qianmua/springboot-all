package pres.qianmuna.netty.chat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * Created by IntelliJ IDEA.
 *
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/5/11
 * @time 22:23
 */
public class CharClient {

    private static final String HOST = "127.0.0.1";
    private static final int PORT = 7777;
    private Selector selector;
    private SocketChannel socketChannel;
    private String user;

    /**
     * init
     */
    public CharClient() throws IOException {
        selector = Selector.open();
        //连接服务
        socketChannel = SocketChannel.open( new InetSocketAddress(HOST , PORT));
        socketChannel.configureBlocking(false);
        //注册 关注事件
        socketChannel.register(selector , SelectionKey.OP_READ);
        //得到user
        user = socketChannel.getRemoteAddress().toString();
        System.out.println(user + " init success");
    }

    /**
     * send
     * @param info
     */
    public void sendInfo(String info){

        info = user + " say ->" + info;

        try {
            socketChannel.write(ByteBuffer.wrap(info.getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * read
     */
    public void readInfo(){

        try {
            int select = selector.select(1000);
            //有可用通道
           if (select > 0){
               Iterator<SelectionKey> iterator = selector.keys().iterator();
               //多通道处理
               while (iterator.hasNext()){

                   SelectionKey next = iterator.next();
                   if (next.isReadable()){
                       SocketChannel socketChannel = (SocketChannel) next.channel();
                       ByteBuffer buffer = ByteBuffer.allocate(1024);
                       socketChannel.read(buffer);

                       String s = new String(buffer.array());
                       System.out.println(s);
                   }else {
//                       System.out.println("无通道");
                   }
               }
               //清除key 防止重复
               iterator.remove();
           }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * start
     * @param args
     */
    public static void main(String[] args) throws IOException {
        //init client
        CharClient charClient = new CharClient();
        //start thread
        //每3s 从服务端读取数据
        new Thread( () ->{
            while (true){
                charClient.readInfo();
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        //发送数据
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()){
            String s = scanner.nextLine();
            charClient.sendInfo(s);
        }

    }
}
