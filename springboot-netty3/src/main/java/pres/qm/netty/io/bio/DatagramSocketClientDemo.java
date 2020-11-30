package pres.qm.netty.io.bio;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;


public class DatagramSocketClientDemo {
    public static void main(String[] args) throws Exception {
        DatagramSocket datagramSocket = new DatagramSocket();
        datagramSocket.connect(new InetSocketAddress("127.0.0.1", 9090));
        byte[] bytes = "1234567".getBytes();

        DatagramPacket datagramPacket = new DatagramPacket(bytes, bytes.length);
        datagramSocket.send(datagramPacket);
        datagramSocket.close();
    }
}
