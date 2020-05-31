package pres.qianmuna.shiro.demo;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.subject.Subject;
import pres.qianmuna.shiro.demo.realm.CustomerRealm;

/**
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/5/31  22:14
 * @description :
 */
public class TestCustomerRealmAuthenticator {

    /*
    * 使用自定义认证
    * realm
    *
    * */


    public static void main(String[] args) {

        // 创建 安全管理
        DefaultSecurityManager securityManager = new DefaultSecurityManager();
        // 自定义
        securityManager.setRealm( new CustomerRealm());
        // 添加到tools
        SecurityUtils.setSecurityManager(securityManager);
        // 得到 sub
        Subject subject = SecurityUtils.getSubject();

        //token
        UsernamePasswordToken token = new UsernamePasswordToken("admin" , "root");

        // login
        try {
            subject.login(token);
            System.out.println(subject.isAuthenticated());
        } catch (AuthenticationException e) {
            e.printStackTrace();
        }
    }
}
