package pers.jc.disuptor;

import com.lmax.disruptor.EventFactory;

/**
 * description :
 *
 * @author jinchao.hu
 * @date 2021/6/8  10:14
 */
public class LongEventFactory implements EventFactory {
    /**
     * 传递事件
     * @return 实例化 事件对象
     */
    @Override
    public Object newInstance() {
        return new LongEvent();
    }
}
