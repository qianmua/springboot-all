package pres.qianmuna.jpa3.controller;

import jdk.nashorn.internal.objects.annotations.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pres.qianmuna.jpa3.dao.M1Mapper;
import pres.qianmuna.jpa3.po.M1Po;

import java.util.List;

/**
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/7/4  23:23
 * @description :
 */
@RestController
public class Test3Controller {

    @Autowired
    private M1Mapper m1Mapper;

    @GetMapping("/m1")
    public List<M1Po> queryById(){
        List<M1Po> m1Pos = m1Mapper.queryByM2id(1L);
        m1Pos.forEach(System.out::println);

        return m1Pos;
    }

}
