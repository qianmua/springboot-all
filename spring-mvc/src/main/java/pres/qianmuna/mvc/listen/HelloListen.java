package pres.qianmuna.mvc.listen;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Created by IntelliJ IDEA.
 *
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/5/14
 * @time 13:58
 */

@WebListener
public class HelloListen implements ServletContextListener {

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("监听1");

    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("监听2");
    }
}
