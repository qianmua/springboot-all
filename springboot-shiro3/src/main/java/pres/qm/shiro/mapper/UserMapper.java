package pres.qm.shiro.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import pres.qm.shiro.pojo.Perms;
import pres.qm.shiro.pojo.User;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 张忆敏
 * @since 2020-11-05
 */
public interface UserMapper extends BaseMapper<User> {

    /**
     * 根据用户名查询业务
     * @param username
     * @return
     */
    User findByUsername(String username);

    /**
     * 根据用户名查询角色信息
     * @param username
     * @return
     */
    User findRolesByUserName(String username);

    /**
     * 根据角色id查询权限信息
     * @param id
     * @return
     */
    List<Perms> findPermsByRoleId(Integer id);
}
