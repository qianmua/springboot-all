package pres.qianmuna.sm;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import pres.qianmuna.sm.config.AppConfig;
import pres.qianmuna.sm.dao.TestDao;
import pres.qianmuna.sm.entity.User2;
import pres.qianmuna.sm.proxy.MyProxy;

import java.lang.reflect.Proxy;

/**
 * Created by IntelliJ IDEA.
 *
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/5/14
 * @time 18:03
 */
public class Init {

    /**
     * mybatis spring 中间件 实现
     *
     * 开闭原则
     *
     * 在不改变原有代码的时候去扩展功能
     *
     * @param args
     */
    public static void main(String[] args) {
        /**
         *
         * 可以通过 @Component + @ComponentScan 将对象注入到ioc
         * 或者Import ， FactoryBean BeanFactoryPostProcessor
         *
         * 完成了 spring 到 mybatis 的整合
         *
         * mapperScan 扫描到interface 交给spring ioc 管理
         * （通过注册器去代理他）
         * 是以一个动态代理对象
         *
         */
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

//        context.getBean("dataSource");
        TestDao bean = context.getBean(TestDao.class);
        bean.queryAll().forEach(System.out::println);

        //动态代理方式
        /**
         *
         * 通过这种 不可以去注册 只能去修改
         *
         * 可以通过 FactoryBean
         * or
         * ImportBeanDefinitionRegistrar
         *
         * spring + mybatis
         *
         * 通过以上几种方式可以代理得到对象
         * 在代理的时候可以得到sqlFactory
         * 处理操作
         *
         *
         */
        TestDao t = (TestDao) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(),
                new Class[]{TestDao.class}, new MyProxy());

//        t.queryAll().forEach(System.out::println);

        User2 user = (User2) context.getBean("user");
        user.say();



        /*
        *
        * mybatis sql 注入问题
        *
        * */

        // # $
        // 静态sql
        // 使用 ？
        // 动态sql
        // 在使用的时候 采取翻译

        // getMapper // 返回了一个代理对象
        // 使用的 是 jdk的动态代理
        //
        // mapper 包含了 sql 的执行


        // @Param

        // 固定生成的
        // 跟你你的 参数 个数生成的
        // 可以在 sql 里面用 param1 param2
        // 或者 arg0 arg1

        // 跟 反射有关系
        //
        // java 8 之前 不支持 不适用 param
        // 反射 的底层
        // 要用到 arg0 1
        // java8 之后 可以 不用 这个 // 直接 通过 name，password 得到参数区



        /// 然后 执行sql
        // 给 $ 赋值
        // 将 # 变为问号

        //动态sql 会直接赋值





    }
}
