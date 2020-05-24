package pres.qianmuna.oauth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by IntelliJ IDEA.
 *
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/5/24
 * @time 22:59
 */
@RestController
public class DemoController {


    @GetMapping("/a/1")
    public String a1(){
        return "A1";
    }
}
