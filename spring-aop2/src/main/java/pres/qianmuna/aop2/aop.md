

# aop

    应用场景
    
        日志
        安全
        事务 （ 开启 提交事务 回滚 ）
        
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
    cglib           子类 代替父类
    ObjenesisCglibAopProxy （4.0 后）  基于cglib 实现 （高效） 延申   
    
    
> 如果 目标类 没有实现接口 就会去 调用 cglib 代理



## aop 拦截

    通过intvoationHandler invoke 方法实现
    
    
## factoryBean BeanFactory


## advice 通知

## pointcut 切点

## advisor 通知器 组合 advice pointcut

    
    
    
    
    
    
    
            
        