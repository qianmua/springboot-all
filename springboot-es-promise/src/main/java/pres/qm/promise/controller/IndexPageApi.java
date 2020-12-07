package pres.qm.promise.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/12/7  10:34
 * @description :
 */
@Controller
public class IndexPageApi {
    @GetMapping("/{page}")
    public String page(@PathVariable String page){

        System.out.println(page);

        return page;
    }

}
