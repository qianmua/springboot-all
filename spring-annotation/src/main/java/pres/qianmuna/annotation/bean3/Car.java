package pres.qianmuna.annotation.bean3;

/**
 * Created by IntelliJ IDEA.
 *
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/5/18
 * @time 12:10
 */
public class Car {

    public Car() {
        System.out.println("1");
    }

    public void init(){
        System.out.println("2");
    }
    public void destroy(){
        System.out.println("3");
    }
}
