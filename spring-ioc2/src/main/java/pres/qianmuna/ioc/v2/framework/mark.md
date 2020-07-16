


# ioc 2.0


## ioc di mvc

    ApplicationCOntext
        getBean(String beanName)
        getBean(Class className)
        
            lazy?
    
    getBean()
    
        BeanDefinition
            className
            beanName...
            
        BeanDefinitionReader // 工具类 读取配置 封装 为 BeanDeinition
        
        BeanWrapper 包装 beanDeinition （ioc?）
        
        
        
## servlet

    applicationCOntext
        beanDefinitionReqder
            doLoadBeanDefinitions
                beanDefitionMap<beanName,BeanDefinition>        
                
        getBean
            beanDefitionMap.get
            instanceBean
            beanWrapper
            populateBean         
            beanWrapper.getWrapperInstance
            
            
            
            
## spring mvc


    dispatcherServlet
    1、ApplicationContext
    2、
    9大组件
    
    多文件上传
    初始化 本地语言环境
    初始化 模板处理器
    handlerMapping
    初始化 参数适配器
    初始化 视图预处理器
    FlashMap管理器          
    
    use  
    
    handlerMapping -> handlerAdapter -> modelAndView -> ViewResolver -> View
    
    
    
    
        
        