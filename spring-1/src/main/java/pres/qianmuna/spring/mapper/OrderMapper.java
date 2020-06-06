package pres.qianmuna.spring.mapper;

import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/6/6  16:09
 * @description :
 */
public interface OrderMapper {

    @Select("select * from product where pid = #{id} ")
    List<Map<String,String>> findById(Long id);
}
