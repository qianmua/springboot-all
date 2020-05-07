package pers.qianmuna.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created by IntelliJ IDEA.
 *
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/5/7
 * @time 15:34
 */
@Configuration
public class SetCorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {

        //允许访问的路径
        registry.addMapping("/**")
                //允许跨域访问的路径
                .allowedOrigins("*")
                //允许请求方法
                .allowedMethods("GET","POST")
                //允许间隔事件
                .maxAge(168000)
                //允许头部设置
                .allowedHeaders("*")
                //是否允许发送Cookie
                .allowCredentials(true);

    }
}
