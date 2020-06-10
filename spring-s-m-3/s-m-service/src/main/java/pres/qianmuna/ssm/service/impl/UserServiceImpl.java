package pres.qianmuna.ssm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pres.qianmuna.ssm.mapping.UserMapping;
import pres.qianmuna.ssm.model.UserModel;
import pres.qianmuna.ssm.service.UserService;

import java.util.List;

/**
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/6/10  17:03
 * @description :
 */
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserMapping userMapping;

    @Override
    public List<UserModel> queryAll() {
        return userMapping.queryAll();
    }
}
