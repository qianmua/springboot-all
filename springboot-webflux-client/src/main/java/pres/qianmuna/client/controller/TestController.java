package pres.qianmuna.client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pres.qianmuna.client.api.UserAPI;
import pres.qianmuna.client.entity.User;
import reactor.core.publisher.Flux;

/**
 * Created by IntelliJ IDEA.
 *
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/5/16
 * @time 18:54
 */
@RestController
public class TestController {

    @Autowired
    UserAPI userAPI;

    @GetMapping("/")
    public void test(){
        Flux<User> userFlux = userAPI.queryall();
        //整个日志？
        userFlux.subscribe(System.out::println);
    }


}
