package pres.qianmuna.spring.bean;

import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;

/**
 * Created by IntelliJ IDEA.
 *
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/5/7
 * @time 10:38
 */
@Component
//@Import()
public class C {

    public C(){
        System.out.println("this is C");
    }

    public C(String name){
//        System.out.println(" i is" + i + " name is  " + name);
        System.out.println("bean C double.");
    }


}
