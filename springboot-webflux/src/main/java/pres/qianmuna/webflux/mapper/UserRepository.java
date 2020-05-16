package pres.qianmuna.webflux.mapper;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import pres.qianmuna.webflux.entity.User;
import reactor.core.publisher.Flux;

/**
 * Created by IntelliJ IDEA.
 *
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/5/16
 * @time 14:57
 */
@Repository
public interface UserRepository extends ReactiveMongoRepository<User , String> {


    /**
     * 根据年龄段查找
     * @param s
     * @param e
     * @return
     */
    Flux<User> findByAgeBetween(int s , int e);
}
