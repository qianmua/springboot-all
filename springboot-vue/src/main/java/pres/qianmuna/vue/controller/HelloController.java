package pres.qianmuna.vue.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/7/7  13:27
 * @description : test
 */
@RestController
public class HelloController {

    @GetMapping("/getInfo")
    public List<String> query1(){
        return Arrays.stream(new String[]{"A", "A", "A", "A"}).collect(Collectors.toList());
    }

    @PostMapping("/getInf02")
    public List<String> query2(){
        return Arrays.stream(new String[]{"A", "A", "A", "A"}).collect(Collectors.toList());
    }

}
