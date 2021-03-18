package pers.qm.uploads;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * description :
 *
 * @author jinchao.hu
 * @date 2021/3/18  15:29
 */
@Controller
public class PageController {

    @GetMapping("/")
    public String index(){
        return "/index";
    }

}
