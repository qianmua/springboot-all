package pres.qianmuna.mvc.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by IntelliJ IDEA.
 *
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/5/18
 * @time 15:34
 */
@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello(){
        return "hello , SUCCESS";
    }
}
