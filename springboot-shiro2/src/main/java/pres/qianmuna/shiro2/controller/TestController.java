package pres.qianmuna.shiro2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/6/16  15:36
 * @description :   Springboot demo
 */
@Controller
public class TestController {


    @GetMapping("/login")
    @ResponseBody
    public String login(){
        System.out.println("login..");
        return "666";
    }


    @GetMapping("/demo")
    public String demo(HttpServletRequest request){
        request.getSession().setAttribute("user" , "111");
        return "index";
    }

}
