package pres.qianmuna.ioc.controller;

import pres.qianmuna.ioc.annotation.Autowired;
import pres.qianmuna.ioc.annotation.Controller;
import pres.qianmuna.ioc.annotation.RequestMapping;
import pres.qianmuna.ioc.annotation.RequestParam;
import pres.qianmuna.ioc.service.InfoService;
import pres.qianmuna.ioc.v2.framework.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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


    /**
     * out
     * @param response response
     * @param str str
     * @return mv
     */
    private ModelAndView print(HttpServletResponse response , String str){

        try {
            response.getWriter().write(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }

}
