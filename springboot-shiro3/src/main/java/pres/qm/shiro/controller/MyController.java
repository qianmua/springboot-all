package pres.qm.shiro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MyController {

    @GetMapping("/toLogin")
    public String toLogin(){
        return "login";
    }
    @GetMapping("/")
    public String index(){
        return "index";
    }
    @GetMapping("/toRegister")
    public String toRegister(){
        return "register";
    }
}
