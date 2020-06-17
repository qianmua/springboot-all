package pres.qianmuna.mvc2;

import org.apache.catalina.LifecycleException;
import pres.qianmuna.mvc2.config.Application;

/**
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/6/17  20:45
 * @description :
 */
public class Init {

    public static void main(String[] args) throws ClassNotFoundException, LifecycleException, InstantiationException, IllegalAccessException {
        Application.run();
    }
}
