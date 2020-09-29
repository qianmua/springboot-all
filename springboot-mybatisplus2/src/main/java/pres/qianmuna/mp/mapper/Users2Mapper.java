package pres.qianmuna.mp.mapper;

import org.apache.ibatis.annotations.Param;
import pres.qianmuna.mp.entity.Users2;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author qianmuna
 * @since 2020-09-02
 */
public interface Users2Mapper extends BaseMapper<Users2> {

    /**
     * 大批量数据插入
     * @param list
     * @return
     */
    Integer saveBatchsMore(@Param("list") List<Users2> list);

}
