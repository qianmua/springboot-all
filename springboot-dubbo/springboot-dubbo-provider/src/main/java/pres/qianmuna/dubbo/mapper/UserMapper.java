package pres.qianmuna.dubbo.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 *
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/5/21
 * @time 18:27
 */
@Mapper
public interface UserMapper {

    @Select("select * from users")
    List<Map<String,Object>> queryAll();
}
