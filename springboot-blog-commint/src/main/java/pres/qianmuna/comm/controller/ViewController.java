package pres.qianmuna.comm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/7/9  22:15
 * @description :
 */
@Controller
public class ViewController {

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/demo1")
    public String demo1(){
        return "demo1";
    }

}
