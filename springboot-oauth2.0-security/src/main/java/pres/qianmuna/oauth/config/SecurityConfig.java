package pres.qianmuna.oauth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Created by IntelliJ IDEA.
 *
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/5/24
 * @time 23:00
 */
@EnableWebSecurity
@Configuration
// 开启 方法拦截 的secured
// 权限验证
@EnableGlobalMethodSecurity( securedEnabled = true  , prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 用户信息服务
     * 查询用户
     * @return
     */
    /*@Bean
    @Override
    protected UserDetailsService userDetailsService() {
        // 内存 形式 创建用户
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();

        manager.createUser(User.withUsername("admin").password("123").authorities("1").build());

        return manager;
    }*/

    /**
     * 密码 比对 编码 校验
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    /**
     * 安全拦截机制
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                // 拦截 /a/** 下面所有请求
                /*
                * 基于 web 授权
                * //
                * 拦截 请求地址 进行 授权验证
                *
                * // 基于 方法 授权
                * //
                * 拦截从 controller 拦截
                * */
                // 使用 方法 拦截
                // 这里 是 基于 web拦截
                /*.antMatchers("/a/1").hasAnyAuthority("1","2")
                // 拥有 1 即可 访问
                .antMatchers("/a/3").hasAuthority("1")
                .antMatchers("/a/2").hasAnyAuthority("2")
                .antMatchers("/a/**").authenticated()*/
                .anyRequest().permitAll()
                .and()
                // 登陆页 定制 提交
                .formLogin()
                .loginPage("/loginPage")
                .loginProcessingUrl("/login")
                .successForwardUrl("/login-success")
                // 会话 管理 创建 规则
                // session
                // 分布式 token 保存 令牌
                // 还有 安全会话等
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .and()
                // 退出登录
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/logout-success");
    }
}
