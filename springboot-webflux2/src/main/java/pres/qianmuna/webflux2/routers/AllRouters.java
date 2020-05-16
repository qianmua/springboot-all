package pres.qianmuna.webflux2.routers;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.*;
import pres.qianmuna.webflux2.handlers.UserHandles;

/**
 * Created by IntelliJ IDEA.
 *
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/5/16
 * @time 17:43
 */
@Configuration
public class AllRouters {
    /**
     * 对应handler
     * 第一个路由规则
     * 跟controller一样
     * @return
     */
    @Bean
    public RouterFunction<ServerResponse> userRouter(UserHandles userHandles){
        //工具类
        // nest 嵌套 条件+ 路由
        return RouterFunctions.nest(
                // 路由接口
                RequestPredicates.path("/user"),RouterFunctions
                        //子路由
                        //getall
                        .route(RequestPredicates.GET("/getall")
                                , userHandles::getAllUser)
                        //创建
                        .andRoute(RequestPredicates.POST("/create")
                                        .and(RequestPredicates.accept(MediaType.valueOf(MediaType.APPLICATION_JSON_VALUE)))
                                ,userHandles::create)
                        //删除
                        .andRoute(RequestPredicates.DELETE("/delete/{id}"),userHandles::delete));

    }
}
