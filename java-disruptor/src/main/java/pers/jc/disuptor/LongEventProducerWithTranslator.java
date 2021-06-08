package pers.jc.disuptor;

import com.lmax.disruptor.EventTranslatorOneArg;
import com.lmax.disruptor.RingBuffer;

import java.nio.ByteBuffer;

/**
 * description :
 *      发布事件
 * @author jinchao.hu
 * @date 2021/6/8  10:22
 */
public class LongEventProducerWithTranslator {

    //一个translator可以看做一个事件初始化器，publicEvent方法会调用它
    //填充Event
    private static final EventTranslatorOneArg<LongEvent, ByteBuffer> TRANSLATOR =
            (event, sequence, bb) -> event.setValue(bb.getLong(0));

    private final RingBuffer<LongEvent> ringBuffer;
    public LongEventProducerWithTranslator(RingBuffer<LongEvent> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    public void onData(ByteBuffer bb) {
        ringBuffer.publishEvent(TRANSLATOR, bb);
    }
}
