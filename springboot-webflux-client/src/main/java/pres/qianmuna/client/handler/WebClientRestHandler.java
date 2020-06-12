package pres.qianmuna.client.handler;

import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import pres.qianmuna.client.entity.MethodsInfo;
import pres.qianmuna.client.entity.ServerInfo;
import reactor.core.publisher.Mono;

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
    public Object invokeRest(ServerInfo serverInfo, MethodsInfo methodInfo) {
        // 处理结果
        Object o = null;

        WebClient.RequestBodySpec req = this.client
                // 请求方法
                .method(methodInfo.getMethod())
                // 地址 和 参数
                .uri(methodInfo.getUrl(), methodInfo.getParams())
                // 格式
                .accept(MediaType.APPLICATION_JSON);
                // 发出请求
        // 判断是否携带 body
        // 发出请求
        WebClient.ResponseSpec retrieve = null;
        if ( methodInfo.getBody() != null){
            retrieve = req.body(methodInfo.getBody(), methodInfo.getBodyElementType()).retrieve();
        }else {
            retrieve = req.retrieve();
        }

        //  处理状态码
        // 判断返回码
        // 状态码
        // 异常
        retrieve.onStatus(s -> s.value() == 404 , e -> Mono.just( new RuntimeException("? not found")));



        if (methodInfo.isReturnFlux()){
            o = retrieve.bodyToFlux(methodInfo.getElementType());
        }else {
            o = retrieve.bodyToMono(methodInfo.getElementType());
        }

        return o;

    }
}
