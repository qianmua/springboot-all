package pers.hjc.listener.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.hjc.listener.service.PbService;

/**
 * description :
 *
 * @author jinchao.hu
 * @date 2021/8/16  10:23
 */
@RestController
@RequestMapping("/test")
public class Test {

    private final PbService pbService;

    public Test(PbService pbService) {
        this.pbService = pbService;
    }

    @GetMapping("/d")
    public String m1(){
        pbService.send();
        return "Success";
    }
}
