

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



## 发送原理

    produceRecord 发送主体
    拦截器
    doSend
    key value 序列化
    分区器
    
    accumulator 发送消息并且 返回结果( 两个线程协同工作， 主线程封装record对象 ， sender线程构成网络I/O)
    
> 生产者参数

    acks 指定分区中必须有多少个副本收到这条消息，之后生产者才会认为消息写入成功
    参数配置时字符串类型
    
    retries 如果错误没有达到会重试，错误达到retries后放弃重试 。 重试间隔100ms
    
    batch.size 批量消息，批次的大小
    
## 消费者，消费者组

    消费者是消费者组 一部分
    
    T1主题 包含 不同分区Partition0-N 
    
    分区 存于 不同消费者组 可以存在于多个消费者组中
    
    区分业务间不同业务消费
 
> 参数设置

    消费者组 默认为空，不能设置为空 ，不然会抛出异常
    客户端Id 默认空 不设置kafkaConsumer会自动生成一个非空字符串

    
    
## 订阅主题 分区

    subscribe 用于订阅
    、
    可以接受一个主题列表 List.asList
    或者使用正则表达式匹配 Pattern.compile("qm*")
    指定分区订阅 consumer.assign(....)
    
    反序列化
    位移提交
    
## 重复消费，消息丢失

    kafka 消息 在分区中保证 顺序消费
    
    位移提交 offset    
    消息都有自己的位置（offset）
    
    重复消费
        两个消费者消费不一致 造成重复消费
        
    消息丢失
        某一个消费者poll消息 ， 提交位移后 ， 服务器宕机
        重启后 重新提交位移，造成消息丢失
        
        
## 自动提交

    消费者管理位移
    
    可能会出现重复消费（某个消费者poll，还未提交位移，加入一个消费者，kafka进行了重平衡，没有更新位移造成重复消费）    
    
## 同步提交



## 指定位移消费    

    seek()
    

## 再均衡监听器

    为消费组高可用，和伸缩性提供保证
    
    就是消息重复消费，消息丢失
    
    在均衡期间 消息不可被消费，STW
    
    // subscribe
    做一个同步位移提交
    `consumer.subscribe(Arrays.asList(topic) , new ConsumerRebalanceListener(){
        ...
        cousumer.commitSync(b)
    })`
    // 记录消费位置
    
## 消费者 拦截器

    对消息处理进行定制
    无效消息处理，消息超时还未消费，处理
    
## 主题 topic
    
    主题元数据保存在zookeeper节点
    
> 创建主题

    bin/kafka-topic.sh --zookeeper localhost:2181 --create --topic topicName --partition 2 --replication-factor 1
    localhost:2181 多个 zookeeper 使用 ， 分割    
    --replication-factor 副本数 ，不能超过节点数
    
> 修改主题

    动作命令 --alter
    
    bin/kafka-topic.sh --alter --zookeeper localhost:2181 --topic topicName --config flush.message=1
    
    
> 删除主题    
    
    bin/kafka-topic.sh --alter --zookeeper localhost:2181 --topic topicName --delete-config flush.message
    
> 增加分区
    
    bin/kafka-topic.sh --alter --zookeeper localhost:2181 --topic topicName --partitiion 3
    
    只能增加不能减少
    
## 分区机制

    topic 包含多个分区
    分区包含多个副本 （broker）
    副本中有个一leader ，当一个leader不可用时 ， 会选举其他副本作为leader
    
> leader 选举

    ISR 与leader保持同步 有资格选举leader
    OSR 跟leader又点滞后
    
## 储存结构

    分区下面都有多个sengment数据段，储存消息
    
> segment 文件结构

    包含index file ， data file 后缀为 .index , .log 索引文件 数据文件
    
## 日志索引

    数据文件分段 ， 文件命名以该段中最小的offser命名 ， 使用使用二分查找可快速查找    
    
    偏移量索引 将索引分割 （稀疏索引） 索引区域 去找指定索引message 二分之查找
    
## 日志删除


## 幂等性

    场景：
    在单个片会话中保证幂等性， 不跨片区
    
## 事务

    弥补幂等性
    可以保证多个分区下写入操作的原子性
    
> 开启

    生产者 开启幂等性
    // 默认为true
    transaction.id 设置为空
    
    
## 控制器

    kafka 集群中 多个 broker节点，其中一个节点会被选举为 控制器 controller
    负责管理 集群中所有分区副本与状态， leader出现问题时，控制器负责该分区重新选举
    
    topic增加分区时，也是控制器负责重新分配
    
    控制器依赖于zoopeeker（zoopeeker leader选举机制）
    zookeeper中存在一个与控制器有关的节点， 持久节点，存放控制器发生变更的次数（控制器纪元）
    
## 可靠性保证

    保证分区消息顺序
    
> 失效副本

    ISR 中副本 读写超时，复制速度跟不上leader速度，从ISR剔除，
    如果跟上后 ，在拉回ISR


## 一致性保证

    leader 挂掉之后 选举新的leader ， 以新leader的 HW 为准，要是老leader数据还未写完
    
    当老leader重启后，会从新leader 的HW开始同步
    （数据不一致）       
    要是 在老leader 回复时有新的消息来，老leader HW 与 新leader HW 同步 导致数据不一致
    
    解决：
    通过一对值（epoch 【leader版本号】 ， offset 【对应该leader写入第一条消息时位移】）
    
    
## 消息重复场景， 解决方案

    生产端重复：
    
        生产者发送没有得到正确broker响应 导致 producer重试
        解决：
        开启幂等性 配置文件：enable.idempotence=true
        ack=0 不充实，可能会造成数据丢失（eg: 日志收集）
        
    消费端重复：
    
        数据消费完没有即时提交offset 到broker
        
        解决方案：
        取消自动提交
        下游做幂等性
        
        
## 消费者组管理

    查看消费者组：
        bin/kafka-consumer-groups.sh --bootstrap-server localhost:9092 --list
        
        
        
## 数据管道 connect

    数据导出 source -> sink
    
    connect

> task
    
    将数据移入移出kafka
    
> worker

    负责管理连接器配置，启动链接器和链接器任务
    提供REST API
    
> 转换器 

    comect 与其他储存系统发送接收数据之间转换
    
## 与文件系统之间数据传递

    