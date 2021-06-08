package pres.qianmuna.jasypt.mapper;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * description :
 *
 * @author jinchao.hu
 * @date 2021/5/25  18:06
 */
public abstract class BaseMapperImpl<T> implements BaseMapper<T>{

    private final Logger logger = LoggerFactory.getLogger(BaseMapperImpl.class);

    @Autowired
    private BaseMapper<T> baseMapper;

    @Override
    public void create(T t) {
        logger.warn("{}" , t);
    }

    public BaseMapper<T> getBaseMapper() {
        return baseMapper;
    }
}
