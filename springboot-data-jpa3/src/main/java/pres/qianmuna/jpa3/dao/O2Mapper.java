package pres.qianmuna.jpa3.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pres.qianmuna.jpa3.po.O2Po;

/**
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/7/4  22:01
 * @description :
 */
@Repository
public interface O2Mapper extends JpaRepository<O2Po , Long> {
}
