package pres.qianmuna.mvc2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/6/17  22:08
 * @description :
 */
@Controller
public class TestController {

    /*
    * handlerMapping
    *
    * 两种 方式
    *
    * @controller
    *
    * 实现Controller
    * HttpRequestHandler
    *
    *
    * controller
    * */


    @RequestMapping("/hello")
    @ResponseBody
    public String demo(){
        return "demo";
    }
}
