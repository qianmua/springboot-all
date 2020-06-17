package pres.qianmuna.shiro2.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pres.qianmuna.shiro2.handler.ShiroCacheManage;
import pres.qianmuna.shiro2.handler.ShiroRealm;

import java.awt.*;
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

        // 注入realm
        manager.setRealm(shiroRealm());
        // 注入 缓存管理
//        manager.setCacheManager(cacheManager());
        return manager;
    }

    /**
     * shiro 缓存 管理
     * @return
     */
    /*@Bean
    public CacheManager cacheManager(){
        return new ShiroCacheManage();
    }*/

    /**
     * 过滤规则
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(){
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();

        bean.setSecurityManager(securityManager());

        bean.setLoginUrl("/login");
        // 没有 权限页面
        bean.setUnauthorizedUrl("/noauth");

        Map<String, String> hashMap = new LinkedHashMap<>();

        hashMap.put("/login/**" , "anon");
        hashMap.put("/css/**" , "anon");
        hashMap.put("/img/**" , "anon");
        hashMap.put("/js/**" , "anon");
        hashMap.put("/lib/**" , "anon");
        hashMap.put("/p1" , "perms[user:add]");
        hashMap.put("/p2" , "perms[user:update]");
        hashMap.put("/p3" , "perms[user:insert]");

        // 过滤规则
        bean.setFilterChainDefinitionMap(hashMap);

        return bean;
    }


    /**
     * shiro bean生命周期 管理
     * @return
     */
    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor(){
        return new LifecycleBeanPostProcessor();
    }

    /**
     * 开启 aop 代理
     * @return
     */
    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator(){
        return new DefaultAdvisorAutoProxyCreator();
    }

    /**
     * 启用 shiro注解
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(){
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager());

        return advisor;
    }

    @Bean
    public ShiroDialect shiroDialect(){
        return new ShiroDialect();
    }



}
