

# kafka

    分布式，支持分区，多副本，基于zookeeper
    
> 特性

    实时处理大数据场景， 低延迟， 流式处理引擎，
    
    采用 scala 编写
    
- 每秒可以处理大量数据（几十万）
- 低延时
- 可扩展，集群热扩展
- 持久性，消息持久到磁盘
- 容错性，允许集群中节点失败
- 高并发，数千个客户端同时读写

## 应用场景

    日志收集 然后统一开放给消费
    
    消息系统 解耦 ， 缓存消息等，
    
    用户活动跟踪 ， 记录用户数据， 然后订阅
    
    运营指标 收集数据 分析
    
    流式处理
    
    
## 设计

**Kakfa Broker Leader**


**Consumergroup**

**Consumer Rebalance**

**Consumer**

**Delivery Mode**

**Topic & Partition**

**Partition Replica**

**Partition leader与follower**

**Topic分配partition和partition replica的算法**

**消息投递可靠性**

    三种模式：
    
- 第一种是啥都不管，发送出去就当作成功，这种情况当然不能保证消息成功投递到broker；
- 第二种是Master-Slave模型，只有当Master和所有Slave都接收到消息时，才算投递成功，这种模型提供了最高的投递可靠性，但是损伤了性能；
- 第三种模型，即只要Master确认收到消息就算投递成功；实际使用时，根据应用特性选择，绝大多数情况下都会中和可靠性和性能选择第三种模型

**Partition ack**

**message状态**

**message持久化**

**message有效期**

**Produer**

**Kafka高吞吐量**

**Kafka delivery guarantee(message传送保证)**

**批量发送**

**Kafka集群中broker之间的关系**

**负载均衡方面**

**分区机制partition**

**离线数据装载**

**实时数据与离线数据**

**插件支持**

**解耦** 相当于一个MQ，使得Producer和Consumer之间异步的操作，系统之间解耦

**冗余** replica有多个副本，保证一个broker node宕机后不会影响整个服务

**扩展性** broker节点可以水平扩展，partition也可以水平增加，partition replica也可以水平增加

**峰值:** 在访问量剧增的情况下，kafka水平扩展, 应用仍然需要继续发挥作用

**可恢复性** 系统的一部分组件失效时，由于有partition的replica副本，不会影响到整个系统

**顺序保证性** 由于kafka的producer的写message与consumer去读message都是顺序的读写，保证了高效的性能。

**缓冲** 
    由于producer那面可能业务很简单，而后端consumer业务会很复杂并有数据库的操作，
    因此肯定是producer会比consumer处理速度快，如果没有kafka，producer直接调用consumer，
    那么就会造成整个系统的处理速度慢，加一层kafka作为MQ，可以起到缓冲的作用。
       
       
**异步通信** 作为MQ，Producer与Consumer异步通信

- Broker：Kafka节点，一个Kafka节点就是一个broker，多个broker可以组成一个Kafka集群。
- Topic：一类消息，消息存放的目录即主题，例如page view日志、以topic的形式存在，Kafka集群能够同时负责多个topic的分发。
- Partition topic物理上的分组，一个topic可以分为多个partition，每个partition是一个有序的队列
- Segment：partition物理上由多个segment组成，每个Segment存着message信息    
- Producer 生产message发送到topic
- Consumer  订阅topic消费message, consumer作为一个线程来消费
- Consumer Group 一个Consumer Group包含多个consumer



## Kafka文件存储机制

     Kafka中发布订阅的对象是topic。
     我们可以为每类数据创建一个topic，把向topic发布消息的客户端称作producer，从topic订阅消息的客户端称作consumer。
     Producers和consumers可以同时从多个topic读写数据
     。一个kafka集群由一个或多个broker服务器组成，它负责持久化和备份具体的kafka消息。
    
## kafka 数据产生消费

    producer send
    序列化器 传输
    分区器 主题A 主题B
    发送消息 ， 消费者消费
    kafka broker 收到消息
     ----> success fail 重试
     
     
## 发送

> 同步发送

    send() 发送后返回一个 Future 对象，然后 future.get() 等待kafka响应，
    正常响应返回消息偏移量
    错误即异常
 
> 异步发送

    producer.send
    
    onCompletion
    
    
## 序列化器

    网络传输序列花化
    //默认字符串序列花器
    还有 整形序列化器
    字节数组序列化器
    
> 自定义序列化器

    实现 Serializer<T> 接口
    
## 分区器

    默认分区策略
    
    消息传递key：
    
        hash（key）%分区数量（numPartions） key相同 分配到同一分区    
        
> 自定义分区器


## 拦截器

    按照规则 过滤不符要求消息
    
    修改消息内容
    
    统计需求
    
> 自定义拦截器    
