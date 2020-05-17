package pres.qianmuna.jpa.mapper;

import org.springframework.data.jpa.repository.JpaRepository;
import pres.qianmuna.jpa.entity.User;

/**
 * Created by IntelliJ IDEA.
 *
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/5/17
 * @time 12:56
 */
public interface UserMapping extends JpaRepository<User , Long> {
}
