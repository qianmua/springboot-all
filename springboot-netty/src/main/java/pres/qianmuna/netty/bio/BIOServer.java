package pres.qianmuna.netty.bio;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.concurrent.*;

/**
 * Created by IntelliJ IDEA.
 *
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/5/9
 * @time 16:18
 */
public class BIOServer {

    public static void main(String[] args) throws IOException {
        // bio 通信模型

        ThreadPoolExecutor th = new ThreadPoolExecutor(2,
                5,
                3,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(3)/*,
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardOldestPolicy()*/
                );

        //创建socket
        ServerSocket socket = new ServerSocket(8989);

        while (true){
            //监听 等待客户端连接
            final Socket accept = socket.accept();
            //main线程
            System.out.println("连接 ->" + Thread.currentThread().getName() + " \t" + Thread.currentThread().getId());
            th.execute( () -> handler(accept));

        }

    }

    private static void handler(Socket socket){
        //创建线程，通讯


        byte[] bytes = new byte[1024];

        //获得输入流
        try {
            InputStream stream = socket.getInputStream();
            //一个服务对应一个线程 (●'◡'●)
            System.out.println("连接 ->" + Thread.currentThread().getName() + " \t" + Thread.currentThread().getId());
            //读取客户端发过来数据
            while (true) {

                int read = stream.read(bytes);
                if (read != -1)
                    //客户端读到的数据
                    System.out.println(Arrays.toString(bytes));
                else
                    break;

            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("断开连接");
        }
    }
}
