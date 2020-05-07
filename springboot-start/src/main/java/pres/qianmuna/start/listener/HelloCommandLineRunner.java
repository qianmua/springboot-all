package pres.qianmuna.start.listener;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * Created by IntelliJ IDEA.
 *
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/5/7
 * @time 18:00
 */
//放在容器
@Component
public class HelloCommandLineRunner implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        System.out.println("runnnnnnnnnnnnnnnnnnnnnnnnn");
        System.out.println(Arrays.asList(args));
    }
}
