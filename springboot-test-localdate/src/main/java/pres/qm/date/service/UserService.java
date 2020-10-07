package pres.qm.date.service;

import pres.qm.date.po.Users;

import java.util.List;

/**
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/10/7  14:38
 * @description :
 */
public interface UserService {

    List<Users> queryAll();

    void add(Users users);
}
