package pres.qm.netty.io.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Iterator;
import java.util.Set;


public class DatagramChannelClientDemo {
    public static void main(String[] args) throws Exception {
        DatagramChannel datagramChannel = DatagramChannel.open();
        datagramChannel.configureBlocking(false);

        Selector selector = Selector.open();
        datagramChannel.register(selector, SelectionKey.OP_WRITE);

        while (datagramChannel.isOpen()) {
            selector.select();
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey selectionKey = iterator.next();
                if (selectionKey.isWritable()) {
                    datagramChannel = (DatagramChannel) selectionKey.channel();
                    datagramChannel.send(ByteBuffer.wrap("123456".getBytes()), new InetSocketAddress("127.0.0.1", 9090));
                    datagramChannel.close();
                }
                iterator.remove();
            }
        }
        selector.close();
    }
}
