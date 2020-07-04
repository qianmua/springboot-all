package pres.qianmuna.jpa3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pres.qianmuna.jpa3.dao.O1Mapper;
import pres.qianmuna.jpa3.dao.O2Mapper;
import pres.qianmuna.jpa3.po.O1Po;
import pres.qianmuna.jpa3.po.O2Po;

import java.util.List;

/**
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/7/4  22:06
 * @description :
 */
@RestController
public class Test2Controller {

    @Autowired
    private O2Mapper o2Mapper;
    @Autowired
    private O1Mapper o1Mapper;

    @GetMapping("/o1")
    public List<O2Po> o1(){
        List<O2Po> all = o2Mapper.findAll();
        all.forEach(System.out::println);
        return all;
    }

    @GetMapping("/o2")
    public List<O1Po> o2(){
        List<O1Po> all = o1Mapper.findAll();
        all.forEach(v1 -> v1.getO2id().forEach(System.out::println));
        return all;
    }


}

