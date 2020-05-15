package pres.qianmuna.druid.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;

/**
 * Created by IntelliJ IDEA.
 *
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/5/15
 * @time 13:12
 */
@RestController
public class TestController {

    @Autowired
    private DataSource dataSource;

    @GetMapping("/test")
    public String test(){

        System.out.println(dataSource.getClass());

        return "SUCCESS";
    }
}
