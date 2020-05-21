package pres.qianmuna.dubbo.service.impl;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Component;
import pres.qianmuna.dubbo.service.UserCService;
import pres.qianmuna.dubbo.service.UserService;

import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 *
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/5/21
 * @time 19:55
 */
@Component
public class UserCServiceImpl implements UserCService {

    @Reference
    private UserService userService;


    @Override
    public List<Map<String, Object>> queryAll() {
        return userService.queryAll();
//        return null;
    }

}
