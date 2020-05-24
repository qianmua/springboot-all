package pres.qianmuna.aop.app;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * Created by IntelliJ IDEA.
 *
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/5/9
 * @time 14:15
 */
/**
 * 切面类
 * */
@Aspect
@Component
public class Test1Aspect {

    /**
     * 切入点， 可以看作是一个集合 最小单位是方法啦~
     *
     *
     * 把切入的类重新包装喽
     *
     * 获取代理对象 返回
     *
     * spring初始化 和 getBean 都会去调用AbstractBeanFactory下面的doGetBean
     * doGetBean下面的这行代码：
     *  Object sharedInstance = getSingleton(beanName);
     * 这个方法对对象进行了代理
     * getSingleton(beanName) //产生了代理对象
     * //getSingleton 下面的这个get得到了代理对象
     * Object singletonObject = this.singletonObjects.get(beanName);
     * //这是一个map 单例池 spring把他扫描初始化好之后，把他变成了一个代理对象
     * //然后放到一个map中 ！ 最后就是 map.get 喽
     * private final Map<String, Object> singletonObjects = new ConcurrentHashMap<>(256);
     *
     * 代理对象 在spring初始化的时候就完成了 ， 就是new AnnotationConfigApplicationContext 的时候
     * // targetObject -> (???) -> proxyObject
     *
     *
     * refresh()
     * finishBeanFactoryInitialization(beanFactory); // 扫描单例对象
     *
     * beanFactory.preInstantiateSingletons(); // 把扫出来的类实例化
     * 实现了这个接口的对象 他里面的方法 先从单例池中拿对象， 没有 ， createBean()
     * 进到里面的if 下面调用了doGetBean() 所以说 getBean 和 初始化都会调用这个doGetBean
     *
     *
     * aop 的动态代理
     *
     * spring aop 实际用到的是 cglib 或者proxy
     *
     * cglib 代理的必须是一个接口
     *
     * 取决于代理的对象是接口
     * 还是 class
     *
     * 原生service -> 代理对象
     * 是init容器是 代理 还是 getBean是代理？
     *
     * 注意doGetBean
     * 启动时调用了一次
     * getBean也调用了一次哦
     *
     * DefaultAopProxyFactory 这个类产生了是cglib 还是 jdk 的代理对象
     * return new ObjenesisCglibAopProxy(config);
     * return new JdkDynamicAopProxy(config);
     *
     * 为什么代理对象必须是一个接口？
     * java 是单继承多实现的 ， 他已经继承了Proxy 类
     * publics final class $Proxy extends Proxy implements UserBean
     *
     *
     *
     *
     *
     *
     *
     * */
    @Pointcut( "execution(* pres.qianmuna.aop.entity..*.*(..))")
    public void web(){}

    @Before("web()")
    public void doBefore(){
        System.out.println("1");
    }

    @After("web()")
    public void doAfter(){
        System.out.println("2");
    }
}
