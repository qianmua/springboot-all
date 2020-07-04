package pres.qianmuna.jpa3.mapper;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pres.qianmuna.jpa3.po.TableOne;

/**
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/7/4  18:42
 * @description :
 */
@Repository
public interface QueryMapper extends JpaRepository<TableOne , Long> {
}
