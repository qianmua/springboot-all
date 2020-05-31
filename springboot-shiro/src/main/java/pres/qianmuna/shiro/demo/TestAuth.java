package pres.qianmuna.shiro.demo;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;

/**
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/5/31  19:46
 * @description :
 */
public class TestAuth {


    public static void main(String[] args) {


        //创建 安全管理器
        DefaultSecurityManager manager = new DefaultSecurityManager();

        //给安全管理器 设置 realm
        // 提供数据
        // 提供认证的 数据 和校验
        manager.setRealm( new IniRealm("classpath:shiro.ini"));

        // 全局工具类 securityUtils
        // 全局安全工具类
        // 设置 安全管理器
        SecurityUtils.setSecurityManager(manager);

        //认证

        // 得到 主体
        Subject subject = SecurityUtils.getSubject();

        //创建令牌
        //
        UsernamePasswordToken token = new UsernamePasswordToken("admin" , "root");

        //用户认证
        // 需要捕获异常
        System.out.println(subject.isAuthenticated());
        try {
            subject.login(token);
            // 认证状态
            System.out.println(subject.isAuthenticated());
        } catch (AuthenticationException e) {
            e.printStackTrace();
        }


    }
}
