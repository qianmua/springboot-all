package pres.qianmuna.shiro2.controller;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

//    @Autowired
//    RedisTemplate redisTemplate;


    @GetMapping("/redis")
    @ResponseBody
    public String redisTest(){
//        redisTemplate.opsForValue().set("k1" , "demo1");

        return "1";
    }

    /*@GetMapping("/login")
    @ResponseBody
    public String login() {
        System.out.println("login..");
        return "666";
    }*/

    @GetMapping("/demo")
    public String demo(HttpServletRequest request, Model model) {
        request.getSession().setAttribute("user", "111");
        model.addAttribute("hello", "hello world");
        return "index";
    }

    @GetMapping("/demo2")
    @ResponseBody
    public String demo2(){
        System.out.println("demo3");
        return"demo2";
    }

    @GetMapping("/demo3")
    @ResponseBody
    public String demo3(){
        System.out.println("demo44");
        return"demo3";
    }


}
