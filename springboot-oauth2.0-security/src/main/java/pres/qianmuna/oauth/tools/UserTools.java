package pres.qianmuna.oauth.tools;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

/**
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/5/28  16:05
 * @description :
 */
@Component
public final class UserTools {

    /**
     * 会话控制
     * @return
     */
    public String getuserName(){

        String userName = "";
        // 框架 的 上下文
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // 就是 用户
        Object details = authentication.getDetails();

        if ( details == null){
             return null;
        }

        if ( details instanceof UserDetails){
             return ((UserDetails) details).getUsername();
        }else {
            return details.toString();
        }
    }
}
