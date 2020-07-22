package pres.qianmuna.rpc.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.serialization.ClassResolver;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

import java.io.File;
import java.net.URL;
import java.util.*;

/**
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/7/20  17:58
 * @description :
 */
public class RpcServer implements Runner{
    /**
     * 注册 表
     * k 业务接口 名
     * v 实现类实例
     */
    private Map<String,Object> rejisterMap = new HashMap<>();

    /**
     * 缓存 包下 业务接口实现类
     */
    private List<String> classCache = Collections.synchronizedList(new ArrayList<>());

    /**
     * 服务 发布
     * @param basePackage Package
     */
    public void publish(String basePackage){
        getProviderClass(basePackage);

        try {
            doRegister();
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
    }

    /**
     * 发布
     */
    private void doRegister() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        if (classCache.isEmpty())
            return;
        // get ins
        for (String s : classCache) {
            Class<?> aClass = Class.forName(s);
            Class<?>[] interfaces = aClass.getInterfaces();
            // 单个接口 单个实现
            if (interfaces.length != 1)
                continue;
            rejisterMap.put(interfaces[0].getName() , aClass.newInstance());

        }

    }

    /**
     * get instance
     * @param basePackage basePackage
     */
    private void getProviderClass(String basePackage) {
        // scan data
        URL url = this.getClass().getClassLoader()
                .getResource(basePackage.replaceAll("\\.", "/"));

        if (null == url)
            return;

        //read file
        File dir = new File(url.getFile());
        // scan .class
        for (File file : Objects.requireNonNull(dir.listFiles())) {
            // dir
            if (file.isDirectory())
                getProviderClass(basePackage + "." + file.getName());

            if (!file.getName().endsWith(".class"))
                continue;
            // CLASS NAME
            classCache.add(basePackage + "." + file.getName().replace(".class" , "").trim());
        }
    }

    @Override
    public void start(){
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup childGroup = new NioEventLoopGroup();

        ServerBootstrap bootstrap = new ServerBootstrap();

        try {
            // bind
            bootstrap.group(bossGroup , childGroup)
                    // 缓冲 队列 // default 50
                    .option(ChannelOption.SO_BACKLOG , 1024)
                    // 长链接  2小时
                    // 启动心跳机制 检测 长连接
                    // 不检测 就 活 2小时
                    .childOption(ChannelOption.SO_KEEPALIVE ,true)
                    // channel
                    .channel(NioServerSocketChannel.class)
                    // channel handler
                    // 解析
                    .childHandler(new ChannelInitializer<SocketChannel>() {
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
                            // 处理 接收 信息
                            pipeline.addLast(new RpcServerHandler(rejisterMap));
                        }
                    });
            // 同步 绑定 //
            // 监听
            ChannelFuture future = bootstrap.bind(8849).sync();
            System.out.println("start server port : 8849");
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
            childGroup.shutdownGracefully();
        }
    }
}
