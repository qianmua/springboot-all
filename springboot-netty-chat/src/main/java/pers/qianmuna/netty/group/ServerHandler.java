package pers.qianmuna.netty.group;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 *
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/5/20
 * @time 21:29
 */
public class ServerHandler extends SimpleChannelInboundHandler<String> {

    /**
     * 管理channel
     * GlobalEventExecutor.INSTANCE 全局事件执行器
     */
    private static ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);


    //使用hashMap添加
    public static Map<String , Channel> channelMap = new HashMap<>();
    // 添login
    public static Map<User , Channel> channelMap2 = new HashMap<>();
    /**
     * 链接建立
     * 连接之后第一个执行
     *
     * 将当前channel 加入到channelGroup
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();

        // 将连接信息 推送给其他客户端
        /*
            channelGroup 遍历channel 发送消息
         */
        channels.writeAndFlush("[client]" + channel.remoteAddress() + "link..\n");
        channels.add(channel);

        channelMap.put("id" , channel);

        // 用户 登录验证
        //可以这样玩
        channelMap2.put(new User("111" , "222") , channel);


    }

    /**
     * 断开连接 触发
     * 推送给其他client
     *
     * 触发会自动从channelGroup移除通道
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();

        channels.writeAndFlush("[client]" + channel.remoteAddress() + "remove..\n");
        //剩余
        System.out.println("channelGroupSize:" + channels.size());

    }

    /**
     * channel 处于活动状态
     *
     * 提示 上线
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(ctx.channel().remoteAddress() + "link ..");
    }

    /**
     * channel 处于非活动状态
     *
     * 提示离线
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(ctx.channel().remoteAddress() + "remove..");
    }

    private SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd HH:mm:ss");
    /**
     *
     * 读取数据
     * 转发
     * 发送数据
     * @param channelHandlerContext
     * @param s
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, String s) throws Exception {
        Channel channel = channelHandlerContext.channel();

        //loop channels 排除自己
        /**
         * 做 单独聊天的话 可以扩展
         */
        channels.forEach( channel1 -> {
            //排除自己 转发
            if (channel1 != channel)
                channel1.writeAndFlush("[user]" + channel.remoteAddress() + "msg -> " + s + sdf.format(new Date()) + "\n");
            else
                channel1.writeAndFlush("[you] send msg -> " + s + sdf.format(new Date()) + "\n");

        });
    }

    /**
     * 处理异常
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
