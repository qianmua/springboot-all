package pres.qianmuna.async.async;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * Created by IntelliJ IDEA.
 *
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/5/10
 * @time 14:14
 */

@Component
//可以添加在方法和类上面
@Async
public class DemoAsync {

    /**
     * 普通异步 无返回
     */
    public void task1(){
        long a1 = System.currentTimeMillis();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long a2 = System.currentTimeMillis();
        System.out.println("ues time " + (a2 - a1));
    }

    public void task2(){
        long a1 = System.currentTimeMillis();
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long a2 = System.currentTimeMillis();
        System.out.println("ues time " + (a2 - a1));
    }


    /**
     * 返回异步结果
     */
    public Future<String> task3(){
        long a1 = System.currentTimeMillis();
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long a2 = System.currentTimeMillis();
        System.out.println("ues time " + (a2 - a1));

        return new AsyncResult<>("task3");
    }
}
