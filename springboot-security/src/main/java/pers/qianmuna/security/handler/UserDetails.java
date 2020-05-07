package pers.qianmuna.security.handler;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * Created by IntelliJ IDEA.
 *
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/5/7
 * @time 16:49
 */

/**
 * 安全模型
 */
public class UserDetails extends User {

    private static final long VERSION_UID =1L;

    public UserDetails(String userName , String password , Collection<? extends GrantedAuthority> authorities){
        this(userName,password,true,true,true,true,authorities);
    }

    public UserDetails(String username,
                       String password,
                       boolean enabled,
                       boolean accountNonExpired,
                       boolean credentialsNonExpired,
                       boolean accountNonLocked,
                       Collection<? extends GrantedAuthority> authorities){

        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
    }

}
