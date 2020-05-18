package pres.qianmuna.annotation.factory;

import org.springframework.beans.factory.FactoryBean;
import pres.qianmuna.annotation.bean2.Yellow;

/**
 * Created by IntelliJ IDEA.
 *
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/5/18
 * @time 12:03
 */
public class TestFactoryBean implements FactoryBean<Yellow> {


    /**
     * 返回一个obj 添加到 ioc里面
     * @return
     * @throws Exception
     */
    @Override
    public Yellow getObject() throws Exception {
        return new Yellow();
    }

    @Override
    public Class<?> getObjectType() {
        return Yellow.class;
    }

    /**
     * 是单例
     * true 是
     * false 不是
     * @return
     */
    @Override
    public boolean isSingleton() {
        return false;
    }
}
