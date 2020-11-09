package pres.qm.shiro.shiro.realms;

import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.CollectionUtils;
import pres.qm.shiro.pojo.Perms;
import pres.qm.shiro.pojo.User;
import pres.qm.shiro.service.UserService;

import javax.annotation.Resource;
import java.util.List;


/**
 * 自定义Realm
 */
public class CustomerRealm extends AuthorizingRealm {

    @Resource
    private UserService userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
        System.out.println("授权");
        //获取当前登录的用户
        String primaryPrincipal = (String) principal.getPrimaryPrincipal();
        //根据主身份获取 角色 和 权限信息
        User user = userService.findRolesByUserName(primaryPrincipal);
        //赋值授权角色
        if (!CollectionUtils.isEmpty(user.getRoles())){
            //给资源进行授权
            SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
            user.getRoles().forEach(role -> {
                info.addRole(role.getName());

                //权限信息
                List<Perms> perms = userService.findPermsByRoleId(role.getId());
                if (!CollectionUtils.isEmpty(perms)){
                    perms.forEach(perm -> {
                        info.addStringPermission(perm.getName());
                    });
                }
            });
            return info;
        }
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("认证");
        String principal = (String) token.getPrincipal();

        User user = userService.findByUsername(principal);

        if (!ObjectUtils.isEmpty(user)){
            return new SimpleAuthenticationInfo(user.getUsername(),user.getPassword(), ByteSource.Util.bytes(user.getSalt()),this.getName());
        }
        return null;
    }
}
