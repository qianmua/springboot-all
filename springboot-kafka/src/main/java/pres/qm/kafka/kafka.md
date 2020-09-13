

# kafka

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