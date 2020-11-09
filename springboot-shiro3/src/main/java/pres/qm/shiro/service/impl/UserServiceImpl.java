package pres.qm.shiro.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.stereotype.Service;
import pres.qm.shiro.mapper.UserMapper;
import pres.qm.shiro.pojo.Perms;
import pres.qm.shiro.pojo.User;
import pres.qm.shiro.service.UserService;
import pres.qm.shiro.utils.SaltUtils;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 张忆敏
 * @since 2020-11-05
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    /**
     * 注册用户
     * @param user
     */
    @Override
    public void register(User user) {
        //处理业务调用dao

        //1.生成随机盐
        String salt = SaltUtils.getSalt(8);
        //2.将随机盐保存到数据库
        user.setSalt(salt);
        //3.根据明文密码进行md5加密 + salt +hash散列
        Md5Hash md5Hash = new Md5Hash(user.getPassword(),salt,1024);
        user.setPassword(md5Hash.toHex());
        this.baseMapper.insert(user);
    }

    /**
     * 根据用户名查询业务
     * @param username
     * @return
     */
    @Override
    public User findByUsername(String username) {
        return this.baseMapper.findByUsername(username);
    }

    @Override
    public User findRolesByUserName(String username) {
     return  this.baseMapper.findRolesByUserName(username);
    }

    @Override
    public List<Perms> findPermsByRoleId(Integer id) {
        return this.baseMapper.findPermsByRoleId(id);
    }

}
