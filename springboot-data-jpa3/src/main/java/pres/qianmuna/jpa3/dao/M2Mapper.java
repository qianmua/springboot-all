package pres.qianmuna.jpa3.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pres.qianmuna.jpa3.po.M2Po;


/**
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/7/4  23:20
 * @description :
 */
@Repository
public interface M2Mapper extends JpaRepository<M2Po,Long> {

}
