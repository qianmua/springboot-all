package pres.qianmuna.client.config;

import lombok.extern.slf4j.Slf4j;
import pres.qianmuna.client.entity.MethodsInfo;
import pres.qianmuna.client.entity.ServerInfo;
import pres.qianmuna.client.handler.RestHandler;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/6/9  22:16
 * @description :
 */
@Slf4j
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
        RestHandler restHandler = null;

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

    private MethodsInfo extractMethodInfo(Method v2, Object[] v3) {

        return null;
    }

    private ServerInfo extractServerInfo(Class<?> type) {
        return null;
    }
}
