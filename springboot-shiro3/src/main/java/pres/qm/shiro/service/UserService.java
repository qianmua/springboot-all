package pres.qm.shiro.service;

import com.baomidou.mybatisplus.extension.service.IService;
import pres.qm.shiro.pojo.Perms;
import pres.qm.shiro.pojo.User;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 张忆敏
 * @since 2020-11-05
 */
public interface UserService extends IService<User> {
    /**
     * 注册用户
     * @param user
     */
    void register(User user);


    /**
     * 根据用户名查询业务
     * @param username
     * @return
     */
    User findByUsername(String username);

    User findRolesByUserName(String username);

    List<Perms> findPermsByRoleId(Integer id);
}
