package pres.qianmuna.jpa3.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pres.qianmuna.jpa3.po.TableOne;
import pres.qianmuna.jpa3.po.TableTow;
import pres.qianmuna.jpa3.vo.ViewInfo;

import java.util.List;

/**
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/7/4  18:42
 * @description :
 */
@Repository
public interface QueryMapper extends JpaRepository<TableOne , Long> {

    @Query( value = "select new pres.qianmuna.jpa3.vo.ViewInfo(v1,v2) from TableOne v1 , TableTow v2" +
            " where v1.tid = v2.tid")
    List<ViewInfo> findViewInfo();
}
