package pres.qianmuna.jpa3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pres.qianmuna.jpa3.dao.QueryMapper;
import pres.qianmuna.jpa3.dao.Table2Mapper;
import pres.qianmuna.jpa3.po.TableOne;
import pres.qianmuna.jpa3.po.TableTow;
import pres.qianmuna.jpa3.service.QueryService;
import pres.qianmuna.jpa3.vo.ViewInfo;

import java.util.List;

/**
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/7/4  18:40
 * @description :
 */
@RestController
public class TestController {

    @Autowired
    private QueryService queryService;

    @Autowired
    private QueryMapper queryMapper;
    @Autowired
    private Table2Mapper table2Mapper;

    @GetMapping("/q1")
    public List<TableOne> q1(){
        List<TableOne> all = queryMapper.findAll();
        all.forEach(System.out::println);
        return all;
    }

    @GetMapping("/q2")
    public List<TableTow> q2(){
        List<TableTow> all = table2Mapper.findAll();
        all.forEach(System.out::println);
        return all;
    }

    @GetMapping("/q3")
    public List<ViewInfo> q3(){
        List<ViewInfo> all = queryMapper.findViewInfo();
        all.forEach(System.out::println);
        return all;
    }
}
