package pres.qianmuna.sm.dao;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 *
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/5/14
 * @time 18:07
 */
public interface TestDao {

    @Select("select * from users")
    List<Map<String , String> > queryAll();

}

