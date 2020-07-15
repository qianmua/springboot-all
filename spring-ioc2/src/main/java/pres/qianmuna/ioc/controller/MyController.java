package pres.qianmuna.ioc.controller;

import pres.qianmuna.ioc.annotation.Autowired;
import pres.qianmuna.ioc.annotation.Controller;
import pres.qianmuna.ioc.annotation.RequestMapping;
import pres.qianmuna.ioc.annotation.RequestParam;
import pres.qianmuna.ioc.service.InfoService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/7/15  20:37
 * @description :
 */
@Controller
@RequestMapping("/demo")
public class MyController {

    @Autowired private InfoService infoService;


    @RequestMapping("/hello")
    public void hello(@RequestParam("name") String name , HttpServletRequest request , HttpServletResponse response){

    }

}
