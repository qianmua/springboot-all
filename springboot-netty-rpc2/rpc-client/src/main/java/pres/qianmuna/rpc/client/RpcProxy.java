package pres.qianmuna.rpc.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/7/22  17:11
 * @description :
 */
public class RpcProxy {

    /**
     * 生成 代理 服务
     * @param testServiceClass T 泛型 方法
     * @return T
     */
    public static <T> T create(Class<?> testServiceClass) {
        return  (T) Proxy.newProxyInstance(testServiceClass.getClassLoader(),
                new Class[]{testServiceClass},
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        // object? 本地 调用
                        if(Object.class.equals(method.getDeclaringClass()))
                            return method.invoke(this , args);

                        // 远程 调用
                        return rpcInvoker(method , args);
                    }
                });
    }

    /**
     * 远程 调用
     * @param method method
     * @param args  args
     * @return Object
     */
    private static Object rpcInvoker(Method method, Object[] args) {

        NioEventLoopGroup group = new NioEventLoopGroup();

        Bootstrap bootstrap = new Bootstrap();
        try {
            bootstrap.group(group)
                    // 类型
                    .channel(NioSocketChannel.class)
                    //
                    .option(ChannelOption.TCP_NODELAY , true)
                    // Nagel / 关闭 // 直接 发送 数据
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {

                            ChannelPipeline pipeline = ch.pipeline();

                            // handler
                            /* 编解码器 */
                            pipeline.addLast(new ObjectEncoder());
                            // 节码 / maxValue / classLoader
                            pipeline.addLast(new ObjectDecoder(Integer.MAX_VALUE ,
                                    ClassResolvers.cacheDisabled(null)));
                            // handler
                            pipeline.addLast();
                        }
                    });

            // link service
            ChannelFuture future = bootstrap.connect("localhost", 8849).sync();

            future.channel().closeFuture().sync();

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            group.shutdownGracefully();
        }

        return null;
    }
}
