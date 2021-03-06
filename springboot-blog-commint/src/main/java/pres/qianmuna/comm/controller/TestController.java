package pres.qianmuna.comm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pres.qianmuna.comm.mapper.AllMapper;
import pres.qianmuna.comm.po.Commit1;
import pres.qianmuna.comm.po.Commit2;
import pres.qianmuna.comm.vo.AllInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/7/9  18:12
 * @description :
 */
@RestController
public class TestController {

    @Autowired
    AllMapper allMapper;

    @GetMapping("/queryall")
    public List<AllInfo> queryall(){
        List<Commit1> allInfos = allMapper.queryComm1();
        List<AllInfo> info = new ArrayList<>();
        allInfos.forEach(v1 -> {
            List<Commit2> commit2s = allMapper.queryById(v1.getCid());
            AllInfo info1 = new AllInfo();
            info1.setCommit1(v1);
            info1.setCommit2(commit2s);
            info.add(info1);
        });
        info.forEach(System.out::println);
//        System.out.println(allInfos);
        return info;
    }

    @GetMapping("/v1")
    public List<Commit1> v1(){
        return allMapper.queryComm1();
    }

    @GetMapping("/v2")
    public List<Commit2> v2(){
        return allMapper.queryComm2();
    }


}
