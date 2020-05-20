package pres.qianmuna.mvc.web.app;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.*;
import pres.qianmuna.mvc.web.interceptor.MyInterceptor;

/**
 * Created by IntelliJ IDEA.
 *
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/5/18
 * @time 15:30
 */

/**
 *
 * 子容器
 * 只扫描controller
 * useDefaultFilters禁用默认的过滤规则
 *
 * 定制 mvc 实现无配置
 *
 */
@ComponentScan(value = "pres,qianmuna.mvc.web" , includeFilters = {
        @ComponentScan.Filter(type = FilterType.ANNOTATION , classes = {Controller.class})
},useDefaultFilters = false)
@EnableWebMvc
public class AppConfig implements WebMvcConfigurer {

    /**
     *
     * 配置视图解析器
     * @param registry
     */
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        //
        registry.jsp("/WEB-INF/views/" , ".jsp");
    }

    /**
     * 静态资源配置
     * 转给 tomcat 去解析
     * 不能去让 mvc 处理
     * @param configurer
     */
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    /**
     * 注册拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new MyInterceptor()).addPathPatterns("/**");
    }
}
