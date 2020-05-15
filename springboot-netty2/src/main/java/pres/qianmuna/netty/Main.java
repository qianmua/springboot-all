package pres.qianmuna.netty;

/**
 * Created by IntelliJ IDEA.
 *
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/5/13
 * @time 23:02
 */
public class Main {

    /**
     *
     * netty 之前NIO ，BIO ，IO 过渡 请看 netty1
     *
     * netty 正题
     *
     * 单reactor单线程
     *
     * 多个客户端连接一个reactor
     * reactor处理连接和事务
     *
     * 单reactor多线程
     *
     * 多个客户端连接一个reactor
     * reactor处理注册处理请求
     * 事务交给子线程
     *
     * 主从多线程
     * reactor只负责注册
     * 事务交给子线程
     *
     * netty
     * 多个reactor 建主从多线程
     *
     *
     * NioEventGroup包含多个NioEventLoop
     * EventLoop包含一个Selector 一个taskQueue
     * NioEventLoop的selector可以注册多个NioChannel
     * 每个NioChannel只会绑定一个NioEventLoop
     * 每个NioChannel绑定一个自己的pipeline
     *
     * */
}
