package pres.qianmuna.webflux2.handlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import pres.qianmuna.webflux2.entity.User;
import pres.qianmuna.webflux2.mapper.UserRepository;
import reactor.core.publisher.Mono;

/**
 * Created by IntelliJ IDEA.
 *
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/5/16
 * @time 17:31
 */
@Component
public class UserHandles {
    private final UserRepository userRepository;

    public UserHandles(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * getAll
     * @param request
     * @return
     */
    public Mono<ServerResponse> getAllUser(ServerRequest request){
        return ServerResponse
                .ok()
                .contentType(MediaType.valueOf(MediaType.APPLICATION_JSON_VALUE))
                .body(this.userRepository.findAll() , User.class);
    }

    /**
     * 创建用户
     * @param request
     * @return
     */
    public Mono<ServerResponse> create(ServerRequest request){
        // 从 请求中拿到数据
        Mono<User> body = request.bodyToMono(User.class);
        return ServerResponse
                .ok()
                .contentType(MediaType.valueOf(MediaType.APPLICATION_JSON_VALUE))
                .body(this.userRepository.saveAll(body) , User.class);
    }

    /**
     * 删除用户
     * @param request
     * @return
     */
    public Mono<ServerResponse> delete(ServerRequest request){
        // 从 请求中拿到数据
//        Mono<User> body = request.bodyToMono(User.class);
        String id = request.pathVariable("id");
        return this.userRepository.findById(id)
                .flatMap( user -> this.userRepository.delete(user)
                        // 返回时没有body的，使用 build构建
                        .then(ServerResponse.ok().build())
                        //不存在
                .switchIfEmpty(ServerResponse.notFound().build()));
    }
}
