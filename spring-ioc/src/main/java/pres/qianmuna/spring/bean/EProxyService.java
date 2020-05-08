package pres.qianmuna.spring.bean;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by IntelliJ IDEA.
 *
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/5/8
 * @time 22:14
 */
public class EProxyService {


    @Autowired
    private EProxy eProxy;

    public void f(){
        System.out.println(eProxy.f());
    }
}
