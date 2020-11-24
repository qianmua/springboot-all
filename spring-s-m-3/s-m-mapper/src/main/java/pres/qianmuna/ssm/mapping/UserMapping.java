package pres.qianmuna.ssm.mapping;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import pres.qianmuna.ssm.model.UserModel;

import java.util.List;

/**
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/6/10  16:57
 * @description :
 */
@Mapper
@Component
public interface UserMapping {

    List<UserModel> queryAll();
}
