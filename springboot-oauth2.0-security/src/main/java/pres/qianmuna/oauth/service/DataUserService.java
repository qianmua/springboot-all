package pres.qianmuna.oauth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pres.qianmuna.oauth.mapper.UserMapper;

import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 *
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/5/25
 * @time 22:34
 */
@Service
public class DataUserService implements UserDetailsService {


    @Autowired
    private UserMapper userMapper;

    /**
     * 根据 账号 查询 用户
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("getUser By Name");

        // password 用 加密 over
        // 模拟
        UserDetails build = User.withUsername("1").password("1").authorities("1").build();

        System.out.println("userName ->.............." + username);
        System.out.println("//================================//");

        //从 数据库中得到 用户名 密码
        List<Map<String, Object>> maps = userMapper.queryByUserName(username);
        // err
        maps.forEach(v1 -> v1.forEach( (v2 , v3) -> System.out.println(v2 + " -----" + v3)));

        // 得到 权限
        List<String> repertory = userMapper.queryRoteByUserId(1L);
        System.out.println("//================================//");
        repertory.forEach(System.out::println);
//        repertory.forEach( stringStringMap -> stringStringMap.forEach((s, s2) -> System.out.println(s + "==========" + s2)));

        // list to String
        String[] array = repertory.toArray(new String[0]);
        // 生成 校验 用户
        UserDetails build2 = User.withUsername(maps.get(0).get("uid").toString()).
                password(maps.get(0).get("password").toString())
                .authorities(array).build();
        return build2;
    }
}
