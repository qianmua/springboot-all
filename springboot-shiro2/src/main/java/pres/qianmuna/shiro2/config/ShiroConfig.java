package pres.qianmuna.shiro2.config;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pres.qianmuna.shiro2.handler.ShiroRealm;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/6/16  17:43
 * @description :
 */
@Configuration
public class ShiroConfig {

    /*
    *
    * shiro
    *
    * 认证 授权
    *
    * 安全管理器 realm
    * 缓存管理器 ehcache             redis
    * 会话管理器 分布式会话管理       redis
    *
    *
    * shiro 启动器 需要 在resources 下面 多一个 shiro.ini 配置 才能去加载 // 配置 ini
    * 初始化
    * // 扩展非常麻烦
    *
    *
    *
    * */


    /**
     * 自定义 realm
     * @return
     */
    @Bean
    public ShiroRealm shiroRealm(){
        return new ShiroRealm();
    }


    /**
     * 配置安全管理器
     * @return
     */
    @Bean
    public SecurityManager securityManager(){
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();

        manager.setRealm(shiroRealm());
        return manager;
    }

    /**
     * 过滤规则
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(){
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();

        bean.setSecurityManager(securityManager());

        bean.setLoginUrl("/login");

        Map<String, String> hashMap = new LinkedHashMap<>();


        hashMap.put("/login" , "anno");
        hashMap.put("/css/**" , "anno");
        hashMap.put("/img/**" , "anno");
        hashMap.put("/js/**" , "anno");
        hashMap.put("/lib/**" , "anno");
        hashMap.put("/**" , "user");

        // 过滤规则
        bean.setFilterChainDefinitionMap(hashMap);

        return bean;
    }



}
