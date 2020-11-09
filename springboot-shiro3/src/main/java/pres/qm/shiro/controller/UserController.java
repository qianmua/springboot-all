package pres.qm.shiro.controller;


import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import pres.qm.shiro.pojo.User;
import pres.qm.shiro.service.UserService;

import javax.annotation.Resource;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 张忆敏
 * @since 2020-11-05
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Resource
    UserService userService;

    /**
     * 用户注册
     */
    @PostMapping("/register")
    public String register(User user){
        try {
            userService.register(user);
            return "login";
        } catch (Exception e) {
            e.printStackTrace();
            return "register";
        }
    }

    @GetMapping("/logout")
    public String logout(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "login";
    }

    /**
     * 用来处理用户认证
     */
    @PostMapping("/login")
    public String login(String username, String password, Model model){
        //获取主题对象
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(new UsernamePasswordToken(username,password));
            return "redirect:/";
        } catch (UnknownAccountException e) {
            model.addAttribute("message","用户名错误");
            e.printStackTrace();
        }catch (IncorrectCredentialsException e){
            model.addAttribute("message","密码错误");
            e.printStackTrace();
        }
        return "login";
    }
}

