package pres.qm.api.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pres.qm.api.annotation.ApiIdempotent;


/**
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/10/27  14:47
 * @description :
 */
@RestController
@Slf4j
@RequestMapping("/test")
public class TestController {

    @GetMapping("/add")
    @ApiIdempotent
    public String test(){
        return "success";
    }
}
