package pres.qianmuna.mbp.service;

import pres.qianmuna.mbp.entity.Users;

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
public interface UserService {

    List<Users> queryByUserName(String name);

}
