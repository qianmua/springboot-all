package pers.jc.disuptor;

import com.lmax.disruptor.RingBuffer;

import java.nio.ByteBuffer;

/**
 * description :
 *          事件源,事件发布器 发布事件
 * @author jinchao.hu
 * @date 2021/6/8  10:18
 */
public class LongEventProducer {
    public final RingBuffer<LongEvent> ringBuffer;

    public LongEventProducer(RingBuffer<LongEvent> ringBuffer){
        this.ringBuffer = ringBuffer;
    }

    /**
     * onData用来发布事件，每调用一次就发布一次事件事件
     * 它的参数会通过事件传递给消费者
     *
     * @param bb
     */public void onData(ByteBuffer bb) {
        //可以把ringBuffer看做一个事件队列，那么next就是得到下面一个事件槽
        long sequence = ringBuffer.next();

        try {
            //用上面的索引取出一个空的事件用于填充
            LongEvent event = ringBuffer.get(sequence);// for the sequence
            event.setValue(bb.getLong(0));
        } finally {
            //发布事件
            ringBuffer.publish(sequence);
        }
    }


}
