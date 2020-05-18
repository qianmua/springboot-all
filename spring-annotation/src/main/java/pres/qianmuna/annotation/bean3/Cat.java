package pres.qianmuna.annotation.bean3;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * Created by IntelliJ IDEA.
 *
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/5/18
 * @time 12:32
 */
public class Cat implements InitializingBean , DisposableBean {


    @Override
    public void destroy() throws Exception {
        System.out.println("end");
    }

    @Override
    public void afterPropertiesSet() throws Exception {

        System.out.println("start");
    }
}
