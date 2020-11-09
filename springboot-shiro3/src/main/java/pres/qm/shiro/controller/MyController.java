package pres.qm.shiro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class MyController {

    @GetMapping("/login")
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

    /*@GetMapping("/{page}")
    public String gotoPage(@PathVariable String page){
        return page;
    }*/
}
