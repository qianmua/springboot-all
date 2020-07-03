package pres.qianmuna.flux.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/7/3  21:42
 * @description :
 */
@RestController
public class HelloMonoController {

    @GetMapping("/mono")
    public Mono<String> mono(){
        return Mono.just("mono");
    }

}
