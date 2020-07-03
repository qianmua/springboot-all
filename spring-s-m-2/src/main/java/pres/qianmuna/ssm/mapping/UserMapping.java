package pres.qianmuna.ssm.mapping;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/7/1  15:12
 * @description :
 */
@Mapper
public interface UserMapping {

    @Select("select * from smbms_user")
    List<Map<String,Object>> queryAll();
}
