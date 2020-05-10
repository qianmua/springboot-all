package pres.qianmuna.exception.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by IntelliJ IDEA.
 *
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/5/10
 * @time 13:47
 */
@RestController
public class DemoController {

    @GetMapping("/test")
    public String test(){
        int i = 1/0;
        return "1";
    }
}
