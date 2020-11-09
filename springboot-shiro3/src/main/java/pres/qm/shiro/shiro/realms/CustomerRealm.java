package pres.qm.shiro.shiro.realms;

import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class CustomerRealm extends AuthorizingRealm {

    @Resource
    private UserService userService;

    /**
     * 授权
     * @param principal context
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {

        log.info("===授权===");

        //获取当前登录的用户
        String primaryPrincipal = (String) principal.getPrimaryPrincipal();

        //根据主身份获取 角色 和 权限信息
        User user = userService.findRolesByUserName(primaryPrincipal);

        //赋值授权角色
        if (!CollectionUtils.isEmpty(user.getRoles())){

            //给资源进行授权
            SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
            // user => role
            user.getRoles().forEach(role -> {
                // 装配 role
                info.addRole(role.getName());

                // 权限信息
                // query perms
                List<Perms> perms = userService.findPermsByRoleId(role.getId());

                if (!CollectionUtils.isEmpty(perms)){
                    // 装配权限
                    perms.forEach(perm -> info.addStringPermission(perm.getName()));
                }

            });
            return info;
        }
        return null;
    }

    /**
     * 认证
     * @param token token
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        log.info("====认证====");

        String principal = (String) token.getPrincipal();
        // search by userName
        User user = userService.findByUsername(principal);

        if (!ObjectUtils.isEmpty(user)){

            // 其他判断
            // 激活
            // 拉黑
            // 禁封
            // 。。。
            // c
            return new SimpleAuthenticationInfo(
                    // userName or user
                    user.getUsername(),
                    // 与 token中比较
                    user.getPassword(),
                    // 对比盐 / SimpleHash hash一次，db salt再一次 区分
                    // 防止初始化密码相同 无法区分
                    ByteSource.Util.bytes(user.getSalt()),
                    // REALM name
                    this.getName());
        }
        return null;
    }
}
