package pres.qianmuna.shiro.demo.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/5/31  22:12
 * @description :
 */
public class CustomerRealm extends AuthorizingRealm {

    /**
     * 授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        return null;
    }

    /**
     * 认证
     * 验证密码
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        // 在token 得到用户名

        String principal = (String) authenticationToken.getPrincipal();
        // get userName
        System.out.println(principal);

        // 查询 数据库
        String userName = "admin";
        String password = "root";

        // 得到用户数据
        // 校验
        if (userName.equals(principal)){
            //三个参数
            // 第三个 realm 名
            // 直接调用父类方法就好了
            SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(userName,password , this.getName());
            return info;
        }


        return null;
    }
}
