package pres.qianmuna.mbp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import pres.qianmuna.mbp.entity.Users;

import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 *
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/5/6
 * @time 18:30
 */
@Repository
public interface UserMapper extends BaseMapper<Users> {

    @Select(" select * from users u , product p, indent i " +
            " where i.uid = u.utid " +
            " and i.pid = p.pid ")
    List<Map<String , Object>> queryAll();
}
