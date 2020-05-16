package pres.qianmuna.webflux.mapper;

import org.apache.ibatis.annotations.Delete;
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
 * @date 2020/5/16
 * @time 14:43
 */
@Mapper
public interface UserMapping {

    /**
     * queryall
     */
    @Select("select * from users")
    List<Map<String , String>> queryall();


    /**
     * query by id
     * @param uid
     * @return
     */
    @Select("select * from users where uid = #{uid} ")
    Map<String,String> queryById(Long uid);

    /**
     * delete by id
     * @param uid
     */
    @Delete("delete from users where uid = #{uid} ")
    void delete(Long uid);

}
