package pres.qianmuna.client.config;

import org.springframework.stereotype.Repository;

/**
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/6/9  21:33
 * @description :
 */
@Repository
public interface ProxyCreator {

    /**
     * 创建代理类
     * @param type
     * @return
     */
    Object createProxy(Class<?> type);
}
