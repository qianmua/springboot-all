package pres.qm.netty.channel;

import java.io.OutputStream;
import java.net.Socket;


public class SocketDemo {

    public static void main(String[] args) throws Exception {

        Socket socket  = new Socket("localhost", 9009);
        OutputStream outputStream = socket.getOutputStream();
        outputStream.write("11111111111".getBytes());
        outputStream.write("22222222222".getBytes());
        outputStream.write("33333333333".getBytes());
        outputStream.flush();
        socket.close();
    }
}
