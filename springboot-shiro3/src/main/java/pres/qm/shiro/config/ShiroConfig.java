package pres.qm.shiro.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pres.qm.shiro.shiro.realms.CustomerRealm;

import java.util.HashMap;
import java.util.Map;

/**
 * 用来整合shiro框架相关的配置类
 */
@Configuration
public class ShiroConfig {

    //1.创建shiroFilter  //负责拦截所有请求
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(DefaultWebSecurityManager defaultWebSecurityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

        //给filter设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);

        //配置系统的受限资源
        //配置系统的公共资源


        /*
            anon : 无需认证，所有的可以访问
            authc : 必须认证了才能访问
            user : 必须拥有记住我功能才能用
            perms : 必须拥有对某个资源的权限才能访问
            role ： 拥有某个角色权限才能访问
         */

        Map<String, String> map = new HashMap<>();
        map.put("/user/login","anon");
        map.put("/user/register","anon");
        map.put("/toRegister","anon");
        map.put("/**","authc");


        //设置默认认证登录界面路径
        shiroFilterFactoryBean.setLoginUrl("/login");

        // filter
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        return shiroFilterFactoryBean;
    }

    //2.创建安全管理器
    @Bean
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("getRealm") Realm realm){
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();

        //给安全管理器设置Realm
        defaultWebSecurityManager.setRealm(realm);
        return defaultWebSecurityManager;
    }

    /**
     * 自定义校验器
     */
    @Bean("getRealm")
    public Realm getRealm(){
        CustomerRealm customerRealm = new CustomerRealm();

        //修改凭证校验匹配器
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        //设置加密算法给md5
        credentialsMatcher.setHashAlgorithmName("MD5");
        //设置散列次数
        credentialsMatcher.setHashIterations(1024);
        customerRealm.setCredentialsMatcher(credentialsMatcher);


        //开启缓存管理
        customerRealm.setCacheManager(new EhCacheManager());
        //开启全局缓存
        customerRealm.setCachingEnabled(true);
        //开启认证的缓存管理
        customerRealm.setAuthenticationCachingEnabled(true);
        //设置认证缓存名称
        customerRealm.setAuthenticationCacheName("authenticationCache");
        //开启授权的缓存管理
        customerRealm.setAuthorizationCachingEnabled(true);
        //设置授权缓存名称
        customerRealm.setAuthorizationCacheName("authorizationCache");
        return customerRealm;
    }

    //4.配置ShiroDialect:用于thymeleaf和shiro标签配合使用
    @Bean
    public ShiroDialect getShiroDialect(){
        return new ShiroDialect();
    }

}
