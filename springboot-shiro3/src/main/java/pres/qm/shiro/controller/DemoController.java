package pres.qm.shiro.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/11/9  15:30
 * @description :
 */
@RestController
@RequestMapping("/api")
@Slf4j
public class DemoController {

    @GetMapping("/q")
    @RequiresRoles( value = {"admin"} , logical = Logical.OR)
    public String q1(){
        log.info("admin auth");
        return "q1";
    }
}
