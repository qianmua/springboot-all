package pres.qianmuna.dubbo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pres.qianmuna.dubbo.service.UserService;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 *
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/5/21
 * @time 19:18
 */
@RestController
public class UserController {

    @Resource
    private UserService userService;
/*
    publics UserController(UserService userService) {
        this.userService = userService;
    }*/

    @GetMapping("/all")
    public List<Map<String , Object>> queryAll(){
        return userService.queryAll();
    }
}
