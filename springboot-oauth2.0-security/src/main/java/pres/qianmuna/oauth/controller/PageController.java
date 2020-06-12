package pres.qianmuna.oauth.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/5/28  16:14
 * @description :
 */
@Controller
public class PageController {


    @GetMapping("/loginPage")
    public String loginPage(){
        return "login";
    }

}
