package pres.qm.date.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pres.qm.date.mapper.UserMapper;
import pres.qm.date.po.Users;

import java.util.List;

/**
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/10/7  14:40
 * @description :
 */
@Service
public class UsersServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;


    @Override
    public List<Users> queryAll() {
        return userMapper.queryAll();
    }

    @Override
    public void add(Users users) {
        userMapper.add(users);
    }
}
