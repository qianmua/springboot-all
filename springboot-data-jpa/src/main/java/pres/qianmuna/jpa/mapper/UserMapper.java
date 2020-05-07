package pres.qianmuna.jpa.mapper;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import pres.qianmuna.jpa.entity.Users;

/**
 * Created by IntelliJ IDEA.
 *
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/5/7
 * @time 13:16
 */
@Component
public interface UserMapper extends CrudRepository<Users , Long> {

}
