package pres.qm.freemarker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/11/6  15:49
 * @description :
 */
@Controller
@RequestMapping("/api")
public class TestController {

    @GetMapping("/{index}")
    public String index(@PathVariable String index , Model model){
        model.addAttribute("name" , "hjc");
        return index;
    }
}
