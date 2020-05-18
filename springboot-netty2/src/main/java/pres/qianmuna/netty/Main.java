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
     *
     * channel 不同协议 不同的阻塞通道 都有不同的channel对应
     * NioSocketChannel 异步客户端
     * NioServerSocketChannel 异步服务端
     * 异步udp
     * 异步客户端sctp
     * 异步sctp服务端
     *
     * selector
     * 管理channel 基于事件驱动
     *
     * channelHandler 处理IO事件
     *  实现类 处理 出站入站事务
     *  事件的驱动方向是客户端到服务端 这位 出站， 反之 则为入站
     *  HandlePipeline 提供了handler 链的容器
     *
     * handler 构成了netty的核心
     *
     * pipeline
     *
     * channelPipeline 是一个handler 的集合 负责处理和拦截inBound 或者outBound的事件 相当于官场netty的链
     *
     *
     *
     *
     *
     *
     *
     * */
}
