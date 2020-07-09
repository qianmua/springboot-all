package pres.qianmuna.comm.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
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

    @Select("select * from commit_1 c1 , commit_2 c2 where c1.cid = c2.cid")
    List<AllInfo> queryAll();

    @Select("select * from commit_1")
    List<Commit1> queryComm1();

    @Select("select * from commit_2")
    List<Commit2> queryComm2();

}
