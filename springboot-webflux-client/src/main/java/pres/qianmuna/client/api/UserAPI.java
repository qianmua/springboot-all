package pres.qianmuna.client.api;

import org.springframework.web.bind.annotation.GetMapping;
import pres.qianmuna.client.anno.ApiServer;
import pres.qianmuna.client.entity.User;
import reactor.core.publisher.Flux;

/**
 * Created by IntelliJ IDEA.
 *
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/5/16
 * @time 18:35
 */

@ApiServer("http://localhost:9090/user")
public interface UserAPI {

    @GetMapping("/queryall")
    Flux<User> queryall();


}
