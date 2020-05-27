package pres.qianmuna.quzrtz.sb.config.handler;

import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.scheduling.quartz.AdaptableJobFactory;
import org.springframework.stereotype.Component;

/**
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/5/27  14:55
 * @description :
 */
@Component("MyJobFactory")
public class JobFactory extends AdaptableJobFactory {


    @Autowired
    private AutowireCapableBeanFactory autowireCapableBeanFactory;


    /**
     *
     * 手动 注入 到spring ioc 容器
     *
     * @param bundle
     * @return
     * @throws Exception
     */
    @Override
    protected Object createJobInstance(TriggerFiredBundle bundle) throws Exception {
        Object o = super.createJobInstance(bundle);
        autowireCapableBeanFactory.autowireBean(o);
        return o;
    }
}
