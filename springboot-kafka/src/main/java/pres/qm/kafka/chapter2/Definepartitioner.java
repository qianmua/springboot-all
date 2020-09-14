package pres.qm.kafka.chapter2;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.utils.Utils;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/9/14  21:20
 * @description : 自定义分区器
 */
public class Definepartitioner implements Partitioner {

    private final AtomicInteger counter = new AtomicInteger(0);

    @Override
    public int partition(String s, Object o, byte[] bytes, Object o1, byte[] bytes1, Cluster cluster) {

        List<PartitionInfo> infos = cluster.partitionsForTopic(s);

        int i = infos.size();

        if (null == bytes)
            return counter.getAndIncrement() % i;
        else
            return Utils.toPositive(Utils.murmur2(bytes)) % i;

    }

    @Override
    public void close() {

    }

    @Override
    public void configure(Map<String, ?> map) {

    }
}
