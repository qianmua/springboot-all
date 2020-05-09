package pres.qianmuna.netty.nio;

import java.nio.channels.Selector;

/**
 * Created by IntelliJ IDEA.
 *
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/5/9
 * @time 20:28
 */
public class NIOSelector {
    /**
     * selector 检测多个注册的通道上面是否有事件发生， 通道以事件的方式注册到selector
     * */

    public static void main(String[] args) {

        /**
         *  // 返回一个set集合 key是 channel ，可以反向得到channel SelectionKey
         *     protected Set<SelectionKey> selectedKeys = new HashSet();
         *     protected HashSet<SelectionKey> keys = new HashSet();
         *     private Set<SelectionKey> publicKeys;
         *     private Set<SelectionKey> publicSelectedKeys;
         * */


        /**
         * servletSocketChannel监听端口
         * 当有channel连接时得到SocketChannel
         * 然后注册到Selector
         * 返回SelectionKey
         * Selector监听 select方法 返回有事件发生的个数 得到所有发生的key
         * 通过key反向得到Channel完成业务处理
         *
         *
         * */
    }
}
