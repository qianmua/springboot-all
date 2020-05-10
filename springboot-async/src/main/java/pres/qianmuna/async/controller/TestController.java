package pres.qianmuna.async.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pres.qianmuna.async.async.DemoAsync;

import java.util.concurrent.Future;

/**
 * Created by IntelliJ IDEA.
 *
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/5/10
 * @time 14:13
 */
@RestController
public class TestController {

    @Autowired
    private DemoAsync demoAsync;

    @GetMapping("/test")
    public String test(){

        long a1 = System.currentTimeMillis();
        demoAsync.task1();
        demoAsync.task2();
        Future<String> future = demoAsync.task3();
        long a2 = System.currentTimeMillis();

        String str = ("task use time -> " + (a2 - a1));

        if (future.isDone()){
            System.out.println("?");
        }

        return str;
    }
}
