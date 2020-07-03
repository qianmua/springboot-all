

# spring mvc

> javaConfig

## tomcat

    tomcat 打破双亲委派原理
    
    //ClassLoad.loadclass()
    //URLClassloader.findclass()
    重新注意制定ClassLoad.loadclass()的loadClass和find classde 方法，从而可以打破双亲的委派机制

    tomcat中的类加载机制
    
    隔离不同应用  部署在同一个Tomcat中的不同应用A和B，例如A用了Spring2.5。B用了Spring3.5，
                    那么这两个应用如果使用的是同一个类加载器，那么Web应用就会因为jar包覆盖而无法启动。
    灵活性         Web应用之间的类加载器相互独立，那么就可以根据修改不同的文件重建不同的类加载器替换原来的。从而不影响其他应用。
    性能          如果在一个Tomcat部署多个应用，多个应用中都有相同的类库依赖。那么可以把这相同的类库让Common类加载器进行加载
    
    
    omcat 有三种类型的类加载器；
    commonLoader,
    catalinaLoader,
    shareoader
    
    
    （打破了双亲委派规则）：
    
    先从JVM的BootStrapClassLoader中加载。
    加载Web应用下/WEB-INF/classes中的类。
    加载Web应用下/WEB-INF/lib/*.jap中的jar包中的类。
    加载上面定义的System路径下面的类。
    加载上面定义的Common路径下面的类。
    
    
    双亲委派：
    
    先从JVM的BootStrapClassLoader中加载。
    加载上面定义的System路径下面的类。
    加载上面定义的Common路径下面的类。
    加载Web应用下/WEB-INF/classes中的类。
    加载Web应用下/WEB-INF/lib/*.jap中的jar包中的类。

> servlet3.0 spi


## mvc

    核心功能
    
    请求分发
    dispatcherServlet // main
    //经过dispatcherServlet 
    dispatcherServlet  -> 继承自HttpServlet
    
    
    -> doService
    
    -> doGet

    
    getHandler = Controller
    
    
    springMVC -> controller 流程：
    
    扫描：sanf project -> 
    得到controller： get Handler(Controller) ->
    遍历方法：forEach method ->
    识别接口：check RequestMapping ->
    存入map：map k put= requestmapping value ->    
    接口 在 map的 key中
    
    发送请求：
    
    拦截请求 拿到uri （接口）
    以 uri 去 map查找 得到 返回值 -> controller (反射调用)
    
    
    //controller 两种类型
    BeanName @controller
    //三种实现类型
    HttpRequestHandler Controller @Controller
    
    //注解 反射调用  -> 得到 Method ——> 得到 参数 数组 （参数处理器 // （27种 参数处理器））—> 参数赋值
    //实现类 调用 接口的实现类
    
    适配器 去 决策 。
    
    
    /// 自定义 参数赋值逻辑
    //  实现 HandlerMethodArgumentResolver 接口
    
    
    
    
## 流程图 

    user -> 发送请求
    dispatcherServlet 接受请求 然后分发
    处理器handlerMapping 处理 查找 handler （适配器 查找 -> 处理 ， 返回结果）
    返回 handler
    //
    寻找视图 渲染 或者 data
    响应用户
    
    
    
    
    
    
    
    
    
    
    
    