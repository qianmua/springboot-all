package pres.qianmuna.client.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import pres.qianmuna.client.anno.ApiServer;
import pres.qianmuna.client.entity.MethodsInfo;
import pres.qianmuna.client.entity.ServerInfo;
import pres.qianmuna.client.handler.RestHandler;
import pres.qianmuna.client.handler.WebClientRestHandler;
import pres.qianmuna.client.tools.PublicTools;
import reactor.core.publisher.Mono;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/6/9  22:16
 * @description :
 */
@Slf4j
@Component
public class JDKProxyCreator implements ProxyCreator {
    /**
     * jdk 代理
     * 实现代理类
     * @param type
     * @return
     */
    @Override
    public Object createProxy(Class<?> type) {

        log.info("create proxy ->" + type);

        ServerInfo serverInfo = extractServerInfo(type);

        log.info("server info ->" + serverInfo);

        // 为每个代理类创建实例
        RestHandler restHandler = new WebClientRestHandler();

        // 初始化服务器信息
        // webclient
        restHandler.init(serverInfo);

        return Proxy.newProxyInstance(this.getClass().getClassLoader() , new Class[]{type} , (v1,v2,v3) -> {

            // 得到 调用信息
            // 根据方法 参数
            MethodsInfo methodInfo = extractMethodInfo(v2,v3);

            log.info("method info->" + methodInfo );

            // 得到rest 信息
            restHandler.invokeRest(serverInfo,methodInfo);

            return null;
        });
    }

    /**
     * 得到 方法调用相关细心
     * @param v2
     * @param v3
     * @return
     */
    private MethodsInfo extractMethodInfo(Method v2, Object[] v3) {
        MethodsInfo info = new MethodsInfo();

        //得到请求方法
        Arrays.stream(v2.getAnnotations()).forEach(
                r -> {
                    // 方法调用类型
                   if (r instanceof GetMapping){
                       GetMapping r1 = (GetMapping) r;
                       info.setUrl(r1.value()[0]);
                       info.setMethod(HttpMethod.GET);
                   }else if (r instanceof PostMapping){
                       PostMapping r1 = (PostMapping) r;
                       info.setUrl(r1.value()[0]);
                       info.setMethod(HttpMethod.POST);
                   }else if (r instanceof PutMapping){
                       PutMapping r1 = (PutMapping) r;
                       info.setUrl(r1.value()[0]);
                       info.setMethod(HttpMethod.PUT);
                   }else if (r instanceof DeleteMapping){
                       DeleteMapping r1 = (DeleteMapping) r;
                       info.setUrl(r1.value()[0]);
                       info.setMethod(HttpMethod.DELETE);
                   }
                }
        );

        Map<String, Object> parms = new LinkedHashMap<>();
        info.setParams(parms);

        // 得到 参数 body
        // 参数携带的注解
        Arrays.stream(v2.getParameters()).forEach(
                PublicTools.consumerWithIndex(
                        (r , i) -> {
                            PathVariable annotation = r.getAnnotation(PathVariable.class);
                            if (annotation != null)
                                parms.put(annotation.value() , v3[i]);
                            RequestBody annotation1 = r.getAnnotation(RequestBody.class);
                            if (annotation1 != null)
                                info.setBody( (Mono<?>) v3[i] );
                        }
                )
        );
        return info;
    }

    /**
     * 得到注解信息
     * @param type
     * @return
     */
    private ServerInfo extractServerInfo(Class<?> type) {
        ServerInfo info = new ServerInfo();

        ApiServer server = type.getAnnotation(ApiServer.class);


        return info;
    }
}
