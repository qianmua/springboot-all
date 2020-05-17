package pres.qianmuna.rest.mapper;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import pres.qianmuna.rest.entity.UserEntity;

/**
 * Created by IntelliJ IDEA.
 *
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/5/17
 * @time 13:43
 */
@RepositoryRestResource( collectionResourceRel = "usertest" , path = "user")
public interface UserMapping extends MongoRepository<UserEntity , String> {
}
