package pres.qianmuna.oauth.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import pres.qianmuna.oauth.tools.UserTools;

/**
 * Created by IntelliJ IDEA.
 *
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/5/24
 * @time 22:59
 */
@RestController
public class DemoController {


    @GetMapping("/a/1")
    // 拥有 p1 权限 即可访问
    @PreAuthorize("hasAnyAuthority('99')")
    public String a1(){
        return "A1";
    }


    @GetMapping("/a/2")
    @PreAuthorize("hasAnyAuthority('p2')")
    public String a2(){
        return "A2";
    }

    @PostMapping("/login-success")
    public String success(){
        // 得到 用户 身份
        return new UserTools().getuserName() + " \t\tSUCCESS";
    }

    @PostMapping("/login")
    public String login(String username , String password){
        System.out.println("提交 到 login？？？");

        return "success";
    }
}