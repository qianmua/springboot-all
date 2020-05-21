package pres.qinamuna.upload.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by IntelliJ IDEA.
 *
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/5/21
 * @time 23:26
 */
@Controller
public class ViewController {

    @GetMapping("/get")
    public String get(){
        return "upload";
    }
}
