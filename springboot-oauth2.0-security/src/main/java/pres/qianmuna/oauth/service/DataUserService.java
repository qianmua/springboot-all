package pres.qianmuna.oauth.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * Created by IntelliJ IDEA.
 *
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/5/25
 * @time 22:34
 */
public class DataUserService implements UserDetailsService {

    /**
     * 根据 账号 查询 用户
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // password 用 加密 over
        UserDetails build = User.withUsername("1").password("1").authorities("1").build();


        return build;
    }
}
