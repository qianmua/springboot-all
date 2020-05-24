package pres.qianmuna.netty.tcpprotoc;

/**
 * Created by IntelliJ IDEA.
 *
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/5/24
 * @time 18:34
 */

import lombok.Data;

/**
 * 协议包
 *
 * 长度
 * 消息
 */
@Data
public class Message {
    private int len;
    private String msg;
}
