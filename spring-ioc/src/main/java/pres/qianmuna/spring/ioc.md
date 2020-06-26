
# 循环依赖

    单例 bean 循环依赖 // spring 缓存 -》
    

> bean 生命周期
    
    扫描类 //
    BeanDefinition // 
    实例 bean //
    填充 // 填充属性 ------------》 可能出现 循环依赖 -> bean2 构造 -> bean2 依赖 bean1 ？ 单例池 没有 // 循环依赖
    执行 指定初始化 //
    beanPostProcessor // 后置处理器 // 加工 （aop（原型bean） 就是 在这里 代理的）
    单例池 （二级缓存）//
    -> 
    生成 bean over
    
    
> 填充 问题 循环依赖 (无法 根本 解决？)

    // 解决 填充-> // 
    在beanA 实例后 放入 map缓存中 （一级缓存） //
    beanB 去缓存中 取A
    
    beanB 注入的a 是原始对象 0.0
    //
    如果 a 发生了代理？ （比如 aop）
    
    
    三级缓存
    
> 三级缓存
    
    ObjectBean
    //
    beanB 需要A时就去取得 A （ObjectBean）去执行的那个方法（getEarlyBeanReference） 生成并且返回 代理 对象 ->
    //
    下面 到a的后置处理器 
    a 也需要去生成代理对象 。

> spring 处理 后续 注入

    aop 代理 提前生成了
    
    问题：
    cglib 代理的话 // cglib 已经包含了 原始bean
    

        
    
    
    
    
    
    
    
    
