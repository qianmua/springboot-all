package pres.qianmuna.netty;

import java.util.Scanner;

/**
 * Created by IntelliJ IDEA.
 *
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/5/9
 * @time 16:17
 */

public class NettyDemoApplication {

    /**
     * netty 架构设计
     *
     * 线程模型： 传统阻塞IO型 reactor 模型（反应器，分发者或者通知者）
     * 就是基于事件，有事件 再分发线程资源
     *
     * 传统阻塞IO型
     *
     * 采用阻塞IO
     * 每个请请求都会建立一个线程，并且阻塞 资源浪费
     *
     * reactor
     *  单 单线程
     *  单 多线程
     *  主从多线程
     *
     *  IO复用监听事件 多个链接公用一个阻塞对象 有处理时在激活
     *  收到事件后在分发给线程
     *  基于线程池复用
     *  （高并发处理关键）
     *
     *  单reactor 单线程
     *  上面的那个聊天，那个server就是一个单reactor
     *  高并发处理还是瓶颈（单线程监听和转发，连接客户端数量一上来就会遇到瓶颈）
     *
     *  单reactor 多线程
     *  如上，监听事件和分配事件分开
     *  reactor收到事件后 通过dispatch分发
     *  如果时连接 -> accept建立连接并且创建handle用来完成连接后的事件
     *  handle负责响应事件，read到数据后在分发给work线程池处理业务逻辑
     *  work线程池处理后在返回handle，最后handle在send给client
     *  （多线程数据共享，访问复杂，reactor主线程还是是单线程，数量上来还是有并发问题，出现瓶颈）
     *
     *
     * 主从reactor多线程
     * 如上将连接注册和事件分发，分开
     * 建立事件注册主线程
     * 连接建立后分发给subReactor（多个）完成后续处理
     * （编程复杂度高）
     *
     * netty模型
     *  netty 基于 主从多线程 ，改进了一些东东
     *  两大线程池
     * bossGroup 线程池 维护accept（注册）事件 ，得到SocketChannel后封装为NIOSocketChannel然后注册到workGroup
     * workGroup 线程池 监听到注册后，分发handle处理
     *
     * 都是NIOEventLoopGroup
     * 相当于事件循环组，（包含多个事件循环）
     * 每个事件循环是NIOEventLoop
     * 每个NIOEventLoop不断轮询执行处理任务
     *
     * Boss：
     * 执行步骤 :
     * 1、轮询accept
     * 2、处理accept事件，与client建立连接，生成NIOSocketChannel，并且注册到workGroup
     * 3、如理任务队列任务
     * Work：
     * 1、轮询read，write
     * 2、处理IO事件，在注册过来的NIOSocketChannel
     * 3、处理任务队列
     * （处理业务时，会使用pipeline管道 ， 通过pipeline可以得到channel）
     *
     *
     *
     *
     *
     * */
    public static void main(String[] args) {

    }
}
