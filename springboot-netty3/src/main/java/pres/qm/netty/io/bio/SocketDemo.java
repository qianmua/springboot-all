package pres.qm.netty.io.bio;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * TCP/IP 同步阻塞客户端
 */
public class SocketDemo {
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("127.0.0.1", 9090);
        OutputStream outputStream = socket.getOutputStream();
        outputStream.write("client:呵呵".getBytes());
        outputStream.flush();
        socket.shutdownOutput();

        InputStream inputStream = socket.getInputStream();
        byte[] buffer = new byte[64];
        int length;
        while ((length = inputStream.read(buffer)) != -1) {
            System.out.println(new String(buffer, 0, length));
        }

        outputStream.close();
        inputStream.close();
        socket.close();
    }
}
