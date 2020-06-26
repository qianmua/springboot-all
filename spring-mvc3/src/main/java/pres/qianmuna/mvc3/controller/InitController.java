package pres.qianmuna.mvc3.controller;

import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/6/26  10:59
 * @description :
 */
@RestController
public class InitController {
    @GetMapping("/index")
    public String hello(@ModelAttribute ModelMap modelMap , Model model){
        return "hello";
    }
}
