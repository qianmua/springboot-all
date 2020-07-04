package pres.qianmuna.jpa3.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pres.qianmuna.jpa3.po.M1Po;

import java.util.List;

/**
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/7/4  23:20
 * @description :
 */
public interface M1Mapper extends JpaRepository<M1Po ,Long> {

    @Query( nativeQuery = true  , value = "select * from table_demo_jpa_m1po where m2id = ?1")
    List<M1Po> queryByM2id(Long id);
}
