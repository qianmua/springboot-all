package pres.qianmuna.dubbo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pres.qianmuna.dubbo.service.UserCService;

import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 *
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/5/21
 * @time 20:05
 */
@RestController
public class ConsumerController {

    private final UserCService userCService;

    public ConsumerController(UserCService userCService) {
        this.userCService = userCService;
    }

    @GetMapping("/test")
    public List<Map<String , Object>> getAll(){
        return userCService.queryAll();
    }
}
