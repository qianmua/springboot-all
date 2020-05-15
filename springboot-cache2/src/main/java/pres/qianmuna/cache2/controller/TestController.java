package pres.qianmuna.cache2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pres.qianmuna.cache2.mapper.UserMapping;

import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 *
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/5/15
 * @time 14:21
 */
@RestController
public class TestController {

    @Autowired
    private UserMapping userMapping;

    @GetMapping("/queryall")
    public List<Map<String, String>> queryAll(){

        return userMapping.queryAll();
    }
}
