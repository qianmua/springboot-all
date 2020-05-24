package pres.qianmuna.dubbo.consumer;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.lang.reflect.Proxy;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by IntelliJ IDEA.
 *
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/5/24
 * @time 21:00
 */
public class NettyClient {

    private static ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    private static NettyClientHandler clientHandler;

    // 得到代理对象
    public Object getBean( final Class<?> serviceClass , final String profix){

        return Proxy.newProxyInstance(
                Thread.currentThread().getContextClassLoader() ,
                new Class<?>[]{serviceClass}, ( p , m , a) -> {
                    System.out.println("Proxy");
                    // 调用一次 该代码就会调用
                    if (clientHandler == null){
                        initClient();
                    }
                    //协议头 args[0]  就是 调用传过来的hello
                    clientHandler.setPara(profix + a[0]);

                    return executorService.submit(clientHandler).get();
                });
    }

    private static void initClient(){
        // 问题解决 没有 初始化 clientHandler
        clientHandler = new NettyClientHandler();


        NioEventLoopGroup group = new NioEventLoopGroup();

        Bootstrap bootstrap = new Bootstrap();

        bootstrap.group(group)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.TCP_NODELAY , true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ChannelPipeline pipeline = ch.pipeline();

                        pipeline.addLast(new StringDecoder());
                        pipeline.addLast(new StringEncoder());

                        pipeline.addLast(clientHandler);
                    }
                });
        try {
            bootstrap.connect("127.0.0.1" , 7000).sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
