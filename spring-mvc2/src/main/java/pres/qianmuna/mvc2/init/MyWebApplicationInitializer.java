package pres.qianmuna.mvc2.init;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import pres.qianmuna.mvc2.config.AppConfig;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

/**
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/6/17  21:00
 * @description :
 */
public class MyWebApplicationInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        //sout
        // init
        System.out.println("init");
        //
        //init spring
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();

        context.register(AppConfig.class);
//        context.refresh();

        DispatcherServlet servlet = new DispatcherServlet(context);

        ServletRegistration.Dynamic dynamic = servletContext.addServlet("app", servlet);

        dynamic.setLoadOnStartup(1);
        dynamic.addMapping("/");


    }
}
