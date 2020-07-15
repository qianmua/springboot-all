


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
        
        
        
        