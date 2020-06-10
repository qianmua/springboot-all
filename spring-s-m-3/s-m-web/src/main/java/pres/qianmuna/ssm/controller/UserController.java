package pres.qianmuna.ssm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import pres.qianmuna.ssm.model.UserModel;
import pres.qianmuna.ssm.service.UserService;

import java.util.List;

/**
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/6/10  17:06
 * @description :
 */
@Controller
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("/queryAll")
    public List<UserModel> queryAll(){
        return userService.queryAll();
    }
}
