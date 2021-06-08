package pers.jc.disuptor;

import com.lmax.disruptor.EventHandler;

/**
 * description :
 *
 * @author jinchao.hu
 * @date 2021/6/8  10:16
 */
public class LongEventHandler implements EventHandler<LongEvent> {

    /**
     * 事件处理器
     *
     * 回调处理器方法
     * @param event
     * @param sequence
     * @param endOfBatch
     * @throws Exception
     */
    @Override
    public void onEvent(LongEvent event, long sequence, boolean endOfBatch) throws Exception {
        System.out.println(event.getValue());
    }
}
