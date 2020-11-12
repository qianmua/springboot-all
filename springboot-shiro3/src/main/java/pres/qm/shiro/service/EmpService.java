package pres.qm.shiro.service;

import pres.qm.shiro.pojo.Emp;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author qianmuna
 * @since 2020-11-12
 */
public interface EmpService extends IService<Emp> {

    List<Emp> getAuthList();
}
