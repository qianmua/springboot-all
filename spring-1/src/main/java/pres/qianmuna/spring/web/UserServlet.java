package pres.qianmuna.spring.web;


import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import pres.qianmuna.spring.appconfig.SpringConfig;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/6/6  15:19
 * @description :
 */
public class UserServlet extends HttpServlet {


    /*
    *
    * spring + servlet
    * */

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 初始化 上下文
        // spring
//        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);

        // 监听 服务器 启动 然后 初始化 spring容器
        // 得到 context//
        ServletContext context = this.getServletContext();
        context.getAttribute("app");
        // 得到 spring context
    }
}
