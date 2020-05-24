package pres.qianmuna.oauth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import pres.qianmuna.oauth.interceptor.SimpleAuthInterceptor;

/**
 * Created by IntelliJ IDEA.
 *
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/5/24
 * @time 22:52
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {


    @Autowired
    private SimpleAuthInterceptor simpleAuthInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(simpleAuthInterceptor).addPathPatterns("/r/**");
    }
}
