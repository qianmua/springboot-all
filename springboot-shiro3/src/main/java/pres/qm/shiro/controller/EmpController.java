package pres.qm.shiro.controller;


import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import pres.qm.shiro.pojo.Emp;
import pres.qm.shiro.service.EmpService;

import java.util.Date;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author qianmuna
 * @since 2020-11-12
 */
@Controller
@RequestMapping("/shiro/emp")
public class EmpController {

    @Autowired
    private EmpService empService;


    @GetMapping("/a/{page}")
    public String a(@PathVariable String page){
        return page;
    }

    @GetMapping("/a/{page}/{id}")
    public String b(@PathVariable String page , @PathVariable Long id , Model model){
        System.out.println("======================================================");
        model.addAttribute("info" , empService.getById(id));
        return page;
    }

    @PostMapping("/update")
    public String update(Emp emp){

        empService.updateById(emp);

        return "redirect:/";
    }

    @PostMapping("/shen")
    public String shen(Emp emp){

        Subject subject = SecurityUtils.getSubject();

        emp.setP1((String) subject.getPrincipal());
        emp.setUpdateTime(new Date());
        empService.updateById(emp);

        return "redirect:/";
    }

    @PostMapping("/add")
    public String add(Emp emp){
        Subject subject = SecurityUtils.getSubject();

        emp.setEName((String) subject.getPrincipal());

        emp.setAuthId(1);
        emp.setDept("开发部");
        emp.setStatus(0);
        emp.setSuc(0);
        emp.setCreateTime(new Date());

        empService.save(emp);

        return "redirect:/";
    }


    @GetMapping("/delete/{id}")
    public String remove(@PathVariable Long id){
        empService.removeById(id);
        return "redirect:/";
    }


}

