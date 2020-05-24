package pres.qianmuna.netty.source;

/**
 * Created by IntelliJ IDEA.
 *
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/5/24
 * @time 19:24
 */
public class Mian {

    /*
    *
    *
    * Server 如何启动？
    *
    * ServerBootStrap 是一个引导类， 用于启动服务器 和引导程序初始化
    * group 把俩线程池 一个 交给了 父类 ，一个交给了自己
    * 然后通过channelFactory 去创建了 channel （通过反射的方式）
    *
    * 然后 handler bossHandler
    * childHandler childHandler
    * 关联 boos ， work 的Handler
    *
    * bind
    * 在这里 进行 了 服务的启动
    * 创建了 一个端口对象 然后非空判断 最后调用核心代码 doBind
    *
    *
    *
    * RPC
    * 远程过程调用 通信协议
    * 该协议允许 A 程序 调用 处于 B服务器 的 B程序
    * 而不用 做额外的编程
    *
    * client 请求 —— > 编码  发送 ——》 服务端 接受 解吗 服务端调用 API 服务端响应 ——》 编码 -> 发送
    * 然后 client 接收 解吗 得到结果
    *
    * RPC 就是 将 上面流程 封装起来 ， 直接调用
    *
    *
    *
    *
    *
    * */
}
