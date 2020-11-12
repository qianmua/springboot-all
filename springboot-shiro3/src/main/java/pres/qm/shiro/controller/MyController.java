package pres.qm.shiro.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pres.qm.shiro.pojo.Emp;
import pres.qm.shiro.pojo.User;
import pres.qm.shiro.service.EmpService;

import java.util.List;

@Controller
public class MyController {

    @Autowired
    private EmpService empService;

    @GetMapping("/login")
    public String toLogin(){
        return "login";
    }
    @GetMapping("/")
    public String index(Model model){


        Subject subject = SecurityUtils.getSubject();
        String name = (String) subject.getPrincipal();
        model.addAttribute("userName" , name);


        // list
        List<Emp> list = empService.getAuthList();
        model.addAttribute("list" , list);

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
