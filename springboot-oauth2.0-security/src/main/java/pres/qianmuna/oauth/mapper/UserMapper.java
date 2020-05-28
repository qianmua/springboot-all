package pres.qianmuna.oauth.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/5/27  23:05
 * @description :
 */
@Mapper
public interface UserMapper {


    @Select("select * from users where  uid = #{userName} ")
    List<Map<String , Object>> queryByUserName(String userName);


}
