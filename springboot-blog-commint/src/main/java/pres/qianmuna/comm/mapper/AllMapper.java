package pres.qianmuna.comm.mapper;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;
import pres.qianmuna.comm.po.Commit1;
import pres.qianmuna.comm.po.Commit2;
import pres.qianmuna.comm.vo.AllInfo;

import java.util.List;

/**
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/7/9  21:04
 * @description :
 */
@Repository
@Mapper
public interface AllMapper {
    @Select("select c1.cid , c1.uid,c1.comm_value , c2.c2id,c2.comm_value as c2comm,c2.from_uid,c2.to_uid  " +
            " from commit_1 c1 , commit_2 c2 " +
            " where c1.cid = c2.cid" )
    @Results( id = "map1" , value = {
            @Result( property = "cid" , column = "cid"),
            /*@Result( property = "commValue" , column = "c2comm"),
            @Result( property = "fromUid" , column = "from_uid"),
            @Result( property = "toUid" , column = "to_uid"),*/
            @Result( property = "commit2" , column = "cid" ,
                    many = @Many(select = "pres.qianmuna.comm.mapper.AllMapper.queryById" , fetchType = FetchType.EAGER)),
    })
    List<AllInfo> queryAll();

    @Select("select * from commit_1")
    List<Commit1> queryComm1();

    @Select(" select * from commit_2 where cid = #{id}")
    List<Commit2> queryById(Long id);

    @Select("select * from commit_2")
    List<Commit2> queryComm2();

}
