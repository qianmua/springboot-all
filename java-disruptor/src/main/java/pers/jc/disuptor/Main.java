package pers.jc.disuptor;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;

import java.nio.ByteBuffer;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * description :
 *
 * @author jinchao.hu
 * @date 2021/6/8  10:23
 */
public class Main {

    /*public static void main(String[] args) throws InterruptedException {
        // Executor that will be used to construct new threads for consumers
        Executor executor = Executors.newCachedThreadPool();
        // The factory for the event
        LongEventFactory factory = new LongEventFactory();
        // Specify the size of the ring buffer, must be power of 2.
        int bufferSize = 1024;
        // Construct the Disruptor
        Disruptor<LongEvent> disruptor = new Disruptor<LongEvent>(factory, bufferSize, executor);

        // Connect the handler
        disruptor.handleEventsWith(new LongEventHandler());
        // Start the Disruptor, starts all threads running
        disruptor.start();
        // Get the ring buffer from the Disruptor to be used for publishing.
        RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();

        LongEventProducer producer = new LongEventProducer(ringBuffer);

        ByteBuffer bb = ByteBuffer.allocate(8);
        for (long l = 0; true; l++) {
            bb.putLong(0, l);

            //发布事件
            producer.onData(bb);
            Thread.sleep(1000);
        }
    }*/


    /**
     * 用lambda表达式来注册EventHandler和EventProductor
     * @param args
     * @throws InterruptedException
     */public static void main(String[] args) throws InterruptedException {
        // Executor that will be used to construct new threads for consumers
        Executor executor = Executors.newCachedThreadPool();
        // Specify the size of the ring buffer, must be power of 2.
        int bufferSize = 1024;// Construct the Disruptor
        Disruptor<LongEvent> disruptor = new Disruptor<>(LongEvent::new, bufferSize, executor);
        // 可以使用lambda来注册一个EventHandler
        disruptor.handleEventsWith((event, sequence, endOfBatch) -> System.out.println("Event: " + event.getValue()));
        // Start the Disruptor, starts all threads running
        disruptor.start();
        // Get the ring buffer from the Disruptor to be used for publishing.
        RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();

        LongEventProducer producer = new LongEventProducer(ringBuffer);

        ByteBuffer bb = ByteBuffer.allocate(8);
        for (long l = 0; true; l++) {
            bb.putLong(0, l);
            ringBuffer.publishEvent((event, sequence, buffer) -> event.setValue(buffer.getLong(0)), bb);
            Thread.sleep(1000);
        }
    }
}
