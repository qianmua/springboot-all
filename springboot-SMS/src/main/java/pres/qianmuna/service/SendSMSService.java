package pres.qianmuna.service;

import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 *
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/5/1
 * @time 22:05
 */
public interface SendSMSService {

    /**
     * 短信发送接口
     * */
    boolean send(String phone , String templateCode , Map<String , Object> code);
}
