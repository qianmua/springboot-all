package pres.qianmuna.mbp.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pres.qianmuna.mbp.mapper.UserMapper;

import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 *
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/5/19
 * @time 13:25
 */
@Controller
@Slf4j
public class TestController {

    private final UserMapper userMapper;


    public TestController(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @GetMapping("/test/queryall")
    public String queryAllInfo(Model model){
        List<Map<String, Object>> maps = userMapper.queryAll();
        log.info("===========================");
        maps.forEach(System.out::println);
        System.out.println("test,test,test");
        log.info("===========================");
        model.addAttribute("list" , maps);
        return "test";
    }
}
