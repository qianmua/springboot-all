package pres.qianmuna.listener.listener;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Created by IntelliJ IDEA.
 *
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/5/15
 * @time 14:04
 */
@Component
public class HelloCommandRunner implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        System.out.println("Command runner......");
    }
}
