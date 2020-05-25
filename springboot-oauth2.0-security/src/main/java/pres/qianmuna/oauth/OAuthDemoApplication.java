package pres.qianmuna.oauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by IntelliJ IDEA.
 *
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/5/24
 * @time 22:57
 */
@SpringBootApplication
public class OAuthDemoApplication {

    /*
    * security 的 三种 权限 投票规则
    *
    *
    * 小心 csrf
    *
    * */

    public static void main(String[] args) {
        SpringApplication.run(OAuthDemoApplication.class ,args);
    }
}
