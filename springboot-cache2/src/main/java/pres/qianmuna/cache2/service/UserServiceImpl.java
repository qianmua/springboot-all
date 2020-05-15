package pres.qianmuna.cache2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import pres.qianmuna.cache2.mapper.UserMapping;

import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 *
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/5/15
 * @time 14:51
 */
@Service
public class UserServiceImpl {

    @Autowired
    UserMapping userMapping;

    /**
     *
     * 缓存原理
     * CacheAutoConfiguration
     * 导入了 11个 缓存配置
     * 存入到 hashMap中
     * 是并发包下面的hashMap
     *
     *
     * 流程
     * 方法调用前去查询cache组件 按照cacheName获取如果没有 则创建
     * 去cache中查找缓存内容使用key 默认是方法参数 ，没有参数 key会自动new SimpleKey
     */

    /**
     * 缓存查到的结果
     *
     * @return
     */
    @Cacheable(cacheNames = "user")
    public List<Map<String , String>> queryAll(){
        return userMapping.queryAll();
    }

    @Cacheable(cacheNames = {"user" , "user2"} , key = "#id")
    public void queryId(Long id){

    }

    /**
     * 修改了值 缓存并且更新缓存
     */
    @CachePut(cacheNames = "user2" , key = "#result.id")
    public void update(){

    }

    /**
     * 缓存清除
     * allEntries 删除当前缓存
     */
    @CacheEvict(cacheNames = "user" , key = "#id"  )
    public void delete(Long id){

    }



}
