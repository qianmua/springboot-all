package pres.qianmuna.spring.mapper;

import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/6/6  16:08
 * @description :
 */
@Mapper
public interface UserMapper {

    /**
     * 一对多 查询
     *
     * 一对一 查询 用 one
     *
     * 多对多查询
     * @return
     */
    @Select("select * from users , product where uid = 1 ")
    @Results({
            @Result( column = "id" , property = "id"),
            @Result(
                    property = "pid" , column = "pid",
                    javaType = List.class,
                    many = @Many(select = "pres.qianmuna.spring.mapper.OrderMapper.findById")
            )
    })
    Map<String ,String> query();



}
