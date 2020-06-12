package pres.qianmuna.client.handler;

import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import pres.qianmuna.client.entity.MethodsInfo;
import pres.qianmuna.client.entity.ServerInfo;

/**
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/6/12  13:18
 * @description :
 */
public class WebClientRestHandler implements RestHandler {

    private WebClient client;

    /**
     * 初始化client
     * @param serverInfo
     */
    @Override
    public void init(ServerInfo serverInfo) {
        this.client = WebClient.create(serverInfo.getUrl());
    }

    /**
     * 处理rest
     * @param serverInfo
     * @param methodInfo
     */
    @Override
    public void invokeRest(ServerInfo serverInfo, MethodsInfo methodInfo) {
        // 处理结果
        Object o = null;

        /*this.client
                // 请求方法
                .method(methodInfo.getMethod())
                // 地址
                .uri(methodInfo.getUrl())
                // 格式
                .accept(MediaType.APPLICATION_JSON)
                // 发出请求
                .retrieve()
                .bodyToFlux(el);*/

    }
}
