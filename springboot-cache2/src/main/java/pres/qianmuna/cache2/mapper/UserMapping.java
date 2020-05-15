package pres.qianmuna.cache2.mapper;

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
 * @date 2020/5/15
 * @time 14:20
 */
@Mapper
public interface UserMapping {

    /**
     * query all
     * @return
     */
    @Select( "select * from users")
    List<Map<String , String>> queryAll();


}
