package pres.qianmuna.ssm;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

/**
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/6/6  16:22
 * @description :
 */
public class Init {


    public static void main(String[] args) {
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(9090);
        tomcat.addContext("/" , "G:\\study\\tom");
        try {
            tomcat.start();
            tomcat.getServer().await();
        } catch (LifecycleException e) {
            e.printStackTrace();
        }
    }
}
