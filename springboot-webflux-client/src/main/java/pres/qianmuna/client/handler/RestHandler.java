package pres.qianmuna.client.handler;

import pres.qianmuna.client.entity.MethodsInfo;
import pres.qianmuna.client.entity.ServerInfo;

/**
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/6/9  22:27
 * @description :
 */
public interface RestHandler {

    /*
     * rest 调用
     * */


    /**
     * 初始化 服务器信息
     * @param serverInfo
     */
    void init(ServerInfo serverInfo);

    /**
     * 调用rest请求 返回 结果
     * @param serverInfo
     * @param methodInfo
     */
    Object invokeRest(ServerInfo serverInfo, MethodsInfo methodInfo);
}
