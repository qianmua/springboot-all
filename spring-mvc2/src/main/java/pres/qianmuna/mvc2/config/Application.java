package pres.qianmuna.mvc2.config;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.LifecycleListener;
import org.apache.catalina.startup.Tomcat;
import pres.qianmuna.mvc2.Init;

/**
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/6/17  20:48
 * @description :
 */
public class Application {


    /**
     * 内嵌tomcat 容器
     */
    public static void run() throws ClassNotFoundException, LifecycleException, IllegalAccessException, InstantiationException {
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8080);
        Context context = tomcat.addContext("/", Init.class.getResource("/").getPath().replace("/", ""));

        context.addLifecycleListener( (LifecycleListener) Class.forName(tomcat.getHost().getConfigClass()).newInstance());
        tomcat.start();
    }
}
