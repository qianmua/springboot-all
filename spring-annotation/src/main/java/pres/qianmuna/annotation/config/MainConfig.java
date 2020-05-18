package pres.qianmuna.annotation.config;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.*;
import pres.qianmuna.annotation.bean.Persoin;
import pres.qianmuna.annotation.bean2.Colors;
import pres.qianmuna.annotation.bean2.Red;
import pres.qianmuna.annotation.condition.WindowsCondition;
import pres.qianmuna.annotation.factory.TestFactoryBean;
import pres.qianmuna.annotation.imports.TestImportSelector;

/**
 * Created by IntelliJ IDEA.
 *
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/5/18
 * @time 11:18
 */
@Configuration
//import 方式注册
//ImportSelector 选择器导入 返回要导入的组件全类名
// 俩个配合 也可以批量导入
/**
 * {@link Configuration @Configuration}, {@link ImportSelector},
 * {@link ImportBeanDefinitionRegistrar}, or regular component classes to import.
 */
//ImportBeanDefinitionRegistrar 实现这个类也可以导入
//FactoryBean 也可以注册 （接口 工厂bean）
@Import({Colors.class , Red.class , TestImportSelector.class})
public class MainConfig {

    /*
    * 配置文件变为 配置类
    * */

    /**
     * 注册到ioc
     * @return
     */
    @Bean
    /**
     * Specifies the name of the scope to use for the annotated component/bean.
     * <p>Defaults to an empty string ({@code ""}) which implies
     * {@link ConfigurableBeanFactory#SCOPE_SINGLETON SCOPE_SINGLETON}.
     * @since 4.2
     * @see ConfigurableBeanFactory#SCOPE_PROTOTYPE
     * @see ConfigurableBeanFactory#SCOPE_SINGLETON
     * @see org.springframework.web.context.WebApplicationContext#SCOPE_REQUEST
     * @see org.springframework.web.context.WebApplicationContext#SCOPE_SESSION
     * @see #value
     *
     * SCOPE_PROTOTYPE 多实例 getBean 的时候才会实例化对象 ， 切记， 单实例IOC 只初始化一次 ， 在IOC加载时
     * SCOPE_SINGLETON 单实例  ioc 启动的时候就会被注册
     * SCOPE_REQUEST 同一个请求
     * SCOPE_SESSION 同一个SESSION
     *
     * 控制作用范围
     */
    //@Scope // 设置实例作用范围

    //懒加载
    //单实例bean  默认在容器启动时便创建了
    // 懒加载 在第一次getBean时完成
    @Lazy
    public Persoin persoin(){
        return new Persoin();
    }

    /**
     * Determine if the condition matches.
     * or {@link org.springframework.core.type.MethodMetadata method} being checked
     * @return {@code true} if the condition matches and the component can be registered,
     * or {@code false} to veto the annotated component's registration
     *
     * 按照条件注册
     */
    @Conditional({WindowsCondition.class})
    @Bean
    public Persoin persoin1(){
        return new Persoin().setAge(1).setName("6");
    }


    /**
     *
     * factory bean 注册
     *
     * 是一个特殊的Bean
     * 工厂Bean 得到的是 他return 的那个Bean
     *  得到它本身 前面加个&
     *  spring 在 FactoryBean 里面定义了这个&
     *
     * @return
     */
    @Bean
    public TestFactoryBean testFactoryBean(){
        return new TestFactoryBean();
    }

}
