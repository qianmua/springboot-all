package pres.qianmuna.spring.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/6/6  15:55
 * @description :
 */
@RestController
public class UserController {

    @GetMapping("/get")
    public String index(){
        return "hello spring mvc";
    }
}
