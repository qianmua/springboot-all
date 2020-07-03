package pres.qianmuna.flux.route;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.net.URI;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

/**
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/7/3  21:10
 * @description :
 */
@Configuration
public class HelloFunctionRoute {

    @Bean(name = "routerFunction")
    public RouterFunction<ServerResponse> routerFunction(){
        return route( req -> {
            // 判断 请求
            URI uri = req.uri();
            return "/hello".equals(uri.getPath());
            // 绑定 实现
        }, req -> ServerResponse.status(HttpStatus.OK)
                .body(Mono.just("hello world"),String.class));
    }

    /**
     * 静态 导入
     * @return  mono
     */
    @Bean(name = "routerFunction1")
    public RouterFunction<ServerResponse> routerFunction1(){
        return route(GET("/hello2"),this::helloWord);
    }

    public Mono<ServerResponse> helloWord(ServerRequest serverRequest){
        return ServerResponse.status(HttpStatus.OK).body(Mono.just("Hello world"),String.class);
    }
}
