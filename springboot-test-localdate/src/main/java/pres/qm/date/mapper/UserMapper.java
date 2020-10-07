package pres.qm.date.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import pres.qm.date.po.Users;

import java.util.List;

/**
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/10/7  14:38
 * @description :
 */
@Mapper
public interface UserMapper {
    @Select( "select * from users")
    List<Users> queryAll();

    @Insert("insert into users( password, tel, name, address, addrName, create_time, update_time, version, deleted) values " +
            " (#{password} , #{tel} ,#{name} ,#{address} ,#{addrName} ,#{create_time} ,#{update_time} ,#{version} ,#{deleted} )")
    @Options( useGeneratedKeys = false , keyColumn = "utid" , keyProperty = "utid")
    void add(Users users);
}
