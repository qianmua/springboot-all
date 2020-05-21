package pres.qianmuna.dubbo.service.impl;

import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import pres.qianmuna.dubbo.entity.User;
import pres.qianmuna.dubbo.mapper.UserMapper;
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
 * @time 18:26
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<Map<String, Object>> queryAll() {
        return userMapper.queryAll();
    }
}
