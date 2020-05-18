package pres.qianmuna.annotation.bean3;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * Created by IntelliJ IDEA.
 *
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/5/18
 * @time 12:34
 */
public class Dog {

    public Dog() {

    }


    @PostConstruct
    public void init(){
        System.out.println("2");
    }


    @PreDestroy
    public void destroy(){
        System.out.println("3");
    }


}
