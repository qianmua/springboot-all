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
