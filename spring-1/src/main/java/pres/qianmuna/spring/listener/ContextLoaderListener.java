package pres.qianmuna.spring.listener;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import pres.qianmuna.spring.appconfig.SpringConfig;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/6/6  15:28
 * @description :
 */
public class ContextLoaderListener implements ServletContextListener {

    /**
     *
     * servlet 提供的
     *
     * 初始化spring容器
     *
     * spring 中用法
     * //
     * 只用在 wen。xml 配置spring listener 就好了
     *
     * @param sce
     */
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);

        // 将spring 上下文 存到 servletContext域
        ServletContext context1 = sce.getServletContext();
        context1.setAttribute("app" , context);
        // 配置 监听器
    }
}
