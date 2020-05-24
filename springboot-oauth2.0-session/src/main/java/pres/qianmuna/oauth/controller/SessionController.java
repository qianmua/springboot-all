package pres.qianmuna.oauth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pres.qianmuna.oauth.model.AuthenticationRequest;
import pres.qianmuna.oauth.service.AuthSessionService;

/**
 * Created by IntelliJ IDEA.
 *
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/5/24
 * @time 22:24
 */
@RestController
public class SessionController {

    private final AuthSessionService sessionService;

    public SessionController(AuthSessionService sessionService) {
        this.sessionService = sessionService;
    }


    @GetMapping("/login")
    public String login(AuthenticationRequest request){
        sessionService.auth(request);

        return "SUCCESS";
    }

}
