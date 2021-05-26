package pres.qianmuna.jasypt.mapper;

import org.springframework.stereotype.Service;

/**
 * description :
 *
 * @author jinchao.hu
 * @date 2021/5/25  18:07
 */
@Service
public class PrintInfo extends BaseMapperImpl<String> implements PrintInfoService{

    @Override
    public void createUser(String ss) {
        getBaseMapper().create(ss);
    }
}
