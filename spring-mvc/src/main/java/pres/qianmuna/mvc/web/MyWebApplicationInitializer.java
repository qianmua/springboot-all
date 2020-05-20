package pres.qianmuna.mvc.web;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import pres.qianmuna.mvc.web.app.AppConfig;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

/**
 * Created by IntelliJ IDEA.
 *
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/5/20
 * @time 13:27
 */
/**
 * java config 第一步 实现 spring 接口
 * */
public class MyWebApplicationInitializer implements WebApplicationInitializer {

    /**
     * onStartup
     * web容器 在启动时候 会调用onStartup ServletContext 对象
     *
     * 为什么tomcat 会加载找个？
     *
     * servlet3.0 新规范  spi
     * 有某些类或者方法需要在启动的时候被tomcat加载
     * 可以用到spi动态扩展机制
     *
     * 手动配置！
     *
     * springboot 自动配置？
     *
     * @param servletContext
     * @throws ServletException
     */
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {

        /* spring 容器 上下文对象 初始化 spring容器*/
        AnnotationConfigApplicationContext applicationContext =
                new AnnotationConfigApplicationContext();

        applicationContext.register(AppConfig.class);

        // 子容器
        // 配置mvc
        DispatcherServlet servlet = new DispatcherServlet((WebApplicationContext) applicationContext);

        // 加入到上下文环境
        ServletRegistration.Dynamic dynamic = servletContext.addServlet("app", servlet);
        dynamic.setLoadOnStartup(1);
        //映射
        dynamic.addMapping("*.do");


    }
}
