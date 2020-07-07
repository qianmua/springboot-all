package pres.qianmuna.scheduled.task;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;

/**
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/7/7  12:33
 * @description :
 */
@Configuration
@EnableScheduling
public class Demo1Annotation {

    @Scheduled( cron = "0/5 * * * * ?")
    //@Scheduled( fixedRate = 5000)
    @Async
    void task1(){
        System.out.println("task" + LocalDateTime.now());

    }

}
