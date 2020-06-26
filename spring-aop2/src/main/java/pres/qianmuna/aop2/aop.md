

# aop

    应用场景
    
        日志
        安全
        事务 （ 开启 提交事务 回滚 ）
        
    是 基于 ioc 的
        
> proxyFactoryBean
    
    核心：
    target
    prixyInterface
    intercaptorNames 通知器 列表 advice pointcut
    
    
    作用：
    创建代理对象
    调用通知器中定义好的方法
    
> spring 代理三种方式

    jdk 动态代理     接口 实现类
    cglib           子类 代替父类 （字节码增强）
    ObjenesisCglibAopProxy （4.0 后 默认）  基于cglib 实现 （高效） 延申   
    
    
> 如果 目标类 没有实现接口 就会去 调用 cglib 代理



## aop 拦截

    通过intvoationHandler invoke 方法实现
    
    
## factoryBean BeanFactory

## advice 通知

## pointcut 切点

## advisor 通知器 组合 advice pointcut


## factory bean

    实现 方法 ， 会暴露 一个 bean
    // 被 他 管理 的一个 bean
    
    getObject 返回 被他管理 的一个 bean
    
    // 得到 当前 bean 加上$

    //eg :
        proxyFactoryBean
    
## bean factory

    (map) chp 并发map

    bean 工厂 //  管理bean
    托管 的 bean 实例 // 单例池。。。
    
    factory bean 也是 被 bean facotry 管理 的 一个 bean
    
    
## proxy factory bean

    factory bean 的实现
    //
    构建一个aop 的代理
    
    AopProxy类 决策 如何代理
    
 
    
    
    
    
    
    
    
    
    
            
        