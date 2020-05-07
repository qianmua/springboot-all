package pers.qianmuna.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Created by IntelliJ IDEA.
 *
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/5/7
 * @time 15:33
 */
@Configuration
@EnableWebSecurity
/**
 * 使用表达式时间方法级别的安全性         4个注解可用
 *
 * @PreAuthorize 在方法调用之前, 基于表达式的计算结果来限制对方法的访问
 *
 * @PostAuthorize 允许方法调用, 但是如果表达式计算结果为false, 将抛出一个安全性异常
 *
 * @PostFilter 允许方法调用, 但必须按照表达式来过滤方法的结果
 *
 * @PreFilter 允许方法调用, 但必须在进入方法之前过滤输入值
 *
 * */
@EnableGlobalMethodSecurity( prePostEnabled = true)
public class SetSecurityConfig extends WebSecurityConfigurerAdapter {


    /**
     * 自定义登录组件
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .cors()
                .and()
                .csrf().disable()
                .authorizeRequests()
                //跨域
                .antMatchers(HttpMethod.OPTIONS , "/**").permitAll()
                //登录URL
                .antMatchers("/login").permitAll()
                //swagger
                .antMatchers("/swagger**/**").permitAll()
                .antMatchers("/v2/**").permitAll()
                //其他请求 统一验证
                .anyRequest().authenticated();

        //退出登录处理器
//        http.logout()/*.logoutSuccessHandler()*/;

        //开启登录认证流程过滤
//        http.addFilterBefore();
        //访问控制时状态检查过滤
//        http.addFilterBefore();

    }
}
