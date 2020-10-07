package pres.qm.date.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pres.qm.date.po.Users;
import pres.qm.date.service.UserService;

import java.util.List;

/**
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/10/7  14:39
 * @description :
 */
@RestController

public class TestController {

    @Autowired
    private UserService userService;

    @GetMapping("/all")
    public List<Users> queryAll(){

        return userService.queryAll();
    }

    @PostMapping("/add")
    public String add(@RequestBody Users users){
        userService.add(users);
        return "success";
    }
}
