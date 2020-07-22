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
import pres.qianmuna.rpc.dto.Invocation;

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
     * @param clazz T 泛型 方法
     * @return T
     */
    public static <T> T create(Class<?> clazz) {
        return  (T) Proxy.newProxyInstance(clazz.getClassLoader(),
                new Class[]{clazz},
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        // object? 本地 调用
                        if(Object.class.equals(method.getDeclaringClass()))
                            return method.invoke(this , args);

                        // 远程 调用
                        return rpcInvoker(method , clazz,args);
                    }
                });
    }

    /**
     * 远程 调用
     * @param method method
     * @param clazz clazz
     * @param args  args
     * @return Object result 远程 结果
     */
    private static Object rpcInvoker(Method method, Class<?> clazz, Object[] args) {

        RpcClientHandler handler = new RpcClientHandler();

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
                            pipeline.addLast(handler);
                        }
                    });

            // link service
            ChannelFuture future = bootstrap.connect("localhost", 8849).sync();

            // send rpc info
            // param
            // init 调用 信息
            Invocation invocation = new Invocation();
            invocation.setClassName(clazz.getName())
                    .setMethodName(method.getName())
                    .setParamType(method.getParameterTypes())
                    .setParamValues(args);
            // send server
            future.channel().writeAndFlush(invocation).sync();

            future.channel().closeFuture().sync();

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            group.shutdownGracefully();
        }

        return handler.getResult();
    }
}
