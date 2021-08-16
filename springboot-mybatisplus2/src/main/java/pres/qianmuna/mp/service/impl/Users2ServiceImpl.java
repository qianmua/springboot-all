package pres.qianmuna.mp.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import pres.qianmuna.mp.entity.Users2;
import pres.qianmuna.mp.mapper.Users2Mapper;
import pres.qianmuna.mp.service.Users2Service;
import pres.qianmuna.mp.utils.UpdateWrapperUtil;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author qianmuna
 * @since 2020-09-02
 */
@Service
public class Users2ServiceImpl extends ServiceImpl<Users2Mapper, Users2> implements Users2Service {

    static final UpdateWrapperUtil.ColumnsMap<Users2> columnsMap = new UpdateWrapperUtil.ColumnsMap<>(10);
    static {
        columnsMap.put(Users2::getUid);
        columnsMap.put(Users2::getAddress);
        columnsMap.put(Users2::getName);
        // super field
        columnsMap.put(Users2::getDate);
    }

    @Override
    public void update(Users2 users3) {
        LambdaUpdateWrapper<Users2> lambdaUpdate = Wrappers.lambdaUpdate(Users2.class);
        UpdateWrapperUtil.getLambdaUpdateWrapper(users3,columnsMap).forEach(lambdaUpdate::set);
        System.out.println(lambdaUpdate.getSqlSet());
    }
}
