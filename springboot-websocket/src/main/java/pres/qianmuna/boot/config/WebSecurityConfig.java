package pres.qianmuna.boot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                //根路径和/login路径不拦截
                .antMatchers("/","/login").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                //2登陆页面路径为/login
                .loginPage("/login")
                //3登陆成功转向chat页面
                .defaultSuccessUrl("/chat")
                .permitAll()
                .and()
                .logout()
                .permitAll();
    }

    /**
     *
     * 4在内存中配置两个用户 wyf 和 wisely ,密码和用户名一致,角色是 USER
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    		auth
                .inMemoryAuthentication()
                    .passwordEncoder(new BCryptPasswordEncoder())
                .withUser("admin").password(new BCryptPasswordEncoder().encode("admin")).roles("USER")
                .and()
                .withUser("abel").password(new BCryptPasswordEncoder().encode("abel")).roles("USER");
    }

    /**
     * 静态资源过滤
     *
     * */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/resources/static/**");
    }

}