package pres.qianmuna.shiro2.handler;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/6/17  13:15
 * @description :
 */
@Slf4j
public class ShiroRealm extends AuthorizingRealm {
    /**
     * 授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        /*
        * 给用户 授权
        * */

        // 从数据库查到角色

        // 然后 检查 权限


        // 授权检查
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        // 添加 角色
        info.addRole("admin");
        // 添加权限
        info.addStringPermission("cms:news:view");
        info.addStringPermission("cms:news:list");

        log.info("rote success...");

        return info;
    }

    /**
     * 认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        // 得到token
        log.info("login system..");
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;

        String username = token.getUsername();

        // 思考 为什么 要 new
        String s = new String(token.getPassword());

        // 查找 用户/////
        ///==========================///
        ///==========================///
        ///==========================///

        if ( !"admin".equals(username) || !"123456".equals(s))
            throw new RuntimeException("username password is error");

        // 创建 信息认证
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(username, s, super.getName());

        log.info("info auth success");
        return info;
    }
}
