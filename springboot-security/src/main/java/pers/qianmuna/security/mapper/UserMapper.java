package pers.qianmuna.security.mapper;

import org.springframework.stereotype.Component;
import pers.qianmuna.security.entity.Users;

import java.util.List;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 *
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/5/7
 * @time 16:42
 */
@Component
public interface UserMapper {

    List queryAll();

    /**
     * 根据用户名查找用户
     * @param name
     * @return
     */
    Users findByName(String name);

    /**
     *  Set<String> permissions = new HashSet<>();
     *         permissions.add("sys:user:view");
     *         permissions.add("sys:user:add");
     *         permissions.add("sys:user:edit");
     *         permissions.add("sys:user:delete");
     * @param name
     * @return
     */
    Set<String> findAuth(String name);
}
