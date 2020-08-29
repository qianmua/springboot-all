package pres.qianmuna.undertow.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/8/29  13:36
 * @description :
 */
@RestController
@RequestMapping("/1")
public class Tests1Controller {


    @GetMapping("/2")
    public String d(){
        return "7777";
    }
}
