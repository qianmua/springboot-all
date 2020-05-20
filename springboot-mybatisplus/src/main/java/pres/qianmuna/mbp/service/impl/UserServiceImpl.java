package pres.qianmuna.mbp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pres.qianmuna.mbp.entity.Users;
import pres.qianmuna.mbp.mapper.UserMapper;
import pres.qianmuna.mbp.service.UserService;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/5/20
 * @time 20:48
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }


    /**
     * 通过用户名查
     * @param name
     * @return
     */
    @Override
    public List<Users> queryByUserName(String name) {
        QueryWrapper<Users> wrapper = new QueryWrapper<>();
        wrapper.eq("name" , name);
        return userMapper.selectList(wrapper);
    }
}
