package pres.qm.netty.io.bio;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * TCP/IP 同步阻塞服务器
 */
public class ServerSocketDemo {

    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(9090);
        Socket socket = serverSocket.accept();
        InputStream inputStream = socket.getInputStream();
        byte[] buffer = new byte[64];
        int length;
        while((length = inputStream.read(buffer)) != -1) {
            System.out.print(new String(buffer, 0, length));
        }
        socket.shutdownInput();

        OutputStream outputStream = socket.getOutputStream();
        outputStream.write("server:哈哈".getBytes());
        outputStream.flush();

        inputStream.close();
        outputStream.close();
        serverSocket.close();
    }

}
