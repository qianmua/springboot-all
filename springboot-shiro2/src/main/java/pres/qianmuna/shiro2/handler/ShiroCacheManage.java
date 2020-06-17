package pres.qianmuna.shiro2.handler;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;

/**
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/6/17  19:27
 * @description : 缓存 管理器
 */
public class ShiroCacheManage implements CacheManager {
    @Override
    public <K, V> Cache<K, V> getCache(String s) throws CacheException {
        // s 缓存名
        return new ShiroCache<>();
    }
}
