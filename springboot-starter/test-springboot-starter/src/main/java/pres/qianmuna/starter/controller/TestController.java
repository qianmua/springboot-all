package pres.qianmuna.starter.controller;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pres.hjc.starter.Hello;

/**
 * Created by IntelliJ IDEA.
 *
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/5/12
 * @time 14:34
 */
@RestController
@Slf4j
public class TestController {

    //private Logger logger = LoggerFactory.getLogger(TestController.class);

    @Autowired
    private Hello hello;

    @GetMapping("/hello")
    public String test(){
        String say = null;
        say = "test";
        //logger.info("test");
        try {
            say = hello.say("what");
            return say;
        } catch (NullPointerException e) {
            e.printStackTrace();
        } finally {
            say = "null point";
        }
        return say;
    }
}
