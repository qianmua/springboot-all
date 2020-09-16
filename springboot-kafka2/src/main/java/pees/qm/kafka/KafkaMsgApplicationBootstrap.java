package pees.qm.kafka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/9/16  21:09
 * @description :
 */
@SpringBootApplication
@RestController
@RequestMapping
public class KafkaMsgApplicationBootstrap {

    public static void main(String[] args) {
        SpringApplication.run(KafkaMsgApplicationBootstrap.class , args);
    }

    @GetMapping("/")
    public String index(){
        return "hello kafka";
    }
}
