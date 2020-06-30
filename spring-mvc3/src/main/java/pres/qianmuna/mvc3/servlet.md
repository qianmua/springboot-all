


#   servlet 

    注册方式
    
        web.xml
        注解  （servlet3.0）
        编码  （java config）
        
        
## 生命周期
    
    servlet
    初始化 init
    服务 service  （doget ， dopost）
    销毁 destroy        
    
    filter
    初始化 init
    过滤 dofilter （责任链）
    销毁  destroy
    
    context
    初始化 contextinit
    销毁  contextdestroy
    
    
## servlet 异步

    支持
    deferredResult  //异步支持
    
    Callable    //  异步执行
    
    CompletionStage    // 流
    
    
    