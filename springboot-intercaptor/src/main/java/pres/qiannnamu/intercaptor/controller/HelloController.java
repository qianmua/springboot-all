package pres.qiannnamu.intercaptor.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by IntelliJ IDEA.
 *
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/5/15
 * @time 12:44
 */
@RestController
public class HelloController {

    @GetMapping("/login")
    public String login(HttpServletRequest request){

        request.getSession().setAttribute("user" , "user");

        return "SUCCESS";
    }
}
