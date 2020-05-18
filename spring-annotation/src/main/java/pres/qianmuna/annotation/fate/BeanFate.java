package pres.qianmuna.annotation.fate;

/**
 * Created by IntelliJ IDEA.
 *
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/5/18
 * @time 12:08
 */
public class BeanFate {

    // ioc 容器 ApplicationContext


    /*
    * bean 生命周期
    *
    * bean 创建 初始化 销毁
    *
    * 由 容器管理
    * 可以自定义
    *
    * 1、指定初始化销毁方法
    * 2、实现initializingBean 定义初始化逻辑 disposableBean 定义销毁
    * 3、使用JSR250 定义的俩注解     @PreDestroy  销毁   @PostConstruct 初始化
    *
    *
    * 4、 bean 后置处理器 BeanPostProcessor
    *   postProcessAfterInitialization
     *   postProcessBeforeInitialization
     *   过程：
     *       遍历得到所有的BeanPostProcessor
     *       先执行 before 然后执行初始化实例 然后执行after
     *       这几个过程在populateBean 之后执行
     *       populateBean 给bean属性赋值
     *
     *
     *   spring中后置处理器应用
     *   在spring中可以用来传递ioc容器
    *   还有数据校验，在前后处理
    *   处理注解 注解的工作 就是 后置处理器完成的 ， 比如 autowired 就是通过后置处理器 去注入值得
    *
    *
    *
    *
    * */
}
