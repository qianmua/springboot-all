package pres.qianmuna.redis;

/**
 * Created by IntelliJ IDEA.
 *
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/5/3
 * @time 20:02
 */
public class Test {


    /*
    * redis base
    *
    * redis 默认有16个数据库
    * 使用select [index] 切换数据库
    * dbsize 查看db大小
    * set , get
    * keys * 查看所有得key
    * flushdb  清空当前库 flushall 清空所有db
    *
    *
    * redis 是单线程的
    * redis的瓶颈是根据内存和网络带框得，所以就设计为单线程
    * redis是将数据存在内存中得
    * 单线程不用cpu上下文去切换，所以快啦
    *
    *
    * redis 基本数据类型
    *
    * String
    * List
    * Set
    * Hash
    * Zset
    *
    *
    * redis 基本命令
    *
    * exists 判断存在
    * move 移除 move key 1
    * expire 设置过期
    * ttl  查看剩余时间
    * type 查看类型
    *
    *
    * String类型
    *
    * append 追加
    * strlen 得到长度
    * incr 自动加一
    * decr 减一
    * incrby decrby 步长
    * getrange 截取长度
    * setrange 替换字符串
    *
    * setex (set with expire) 设置过期时间
    * setnx （set if not exist） 不存在设置 (分布式锁常用)
    *
    * mset 批量添加
    * mget 批量get
    * msetnx 是一个原子性操作，一起成功
    *
    * key的巧妙使用 // 可以达到key的复用
    * mset user：1：name zhang user:1:age 18
    * mset user：2：name zhang user:2:age 18
    * mget user:1:name suer:1:age
    *
    *组合
    *
    * getset 有则返回并且替换 ， 没有则返回nil并且添加
    *
    * List类型
    * 所有的list命令以l开头
    * lpush list1 1
    * lpush list1 2
    * lpush list1 3
    *
    * lrange list1 0 -1 获得值 //方式跟栈一样的
    * rpush 插入到最后一个位置
    * lpop list1 移除
    * rpop
    * lindex list 1通过下标得到值
    * llen 得到长度
    * lrem list1 2 name 移除几个指定值
    * ltrim list1 1 2截断
    * rpoplpush 弹出最后一个，并添加到新list
    * exists 列表是否存在
    *
    * lset 更新操作 ，不存在报错
    *
    * lisnert 给某个key前面或者后面插入
    *
    * 实际上是一个链表
    *
    *
    * Set类型
    * set中的值不可以重复，无序的哦
    *
    * sadd 添加
    * smembers 查看
    * sismermber set中是否包含某个值
    * scard 得到大小
    * srem 移除
    * srandmember 随机得到一个成员
    * spop 随机弹出
    * smove 移动元素
    * sdiff 两个set差集
    * sinter 交集
    * sunion 并集
    *
    * Hash类型
    * 跟map集合差不多
    * key相同就覆盖
    * 本质跟string一样
    *
    * hset
    * hget
    * hmset
    * hgetall
    * hdel 移除指定key
    * hlen 得到长度
    * hexists 判断key值存在
    * hkeys 得到key
    * hvals 得到value
    * hincrby 给value+1
    * hsetnx 存在怎么不存在怎么
    * 跟string操作差不多前面多了个h
    *
    * Zset类型
    * 在set的基础上增加了一个值 排序
    *
    * zadd myset 1 one 就是加了个版本号
    * zadd myset 2 two
    * zrangebyscore myset -inf +inf 排序所有
    * zrevrange myset 0 -1 从大到小排序
    * zrem 移除
    * zcount 大小
    *
    *
    * 三种特数的数据类型
    *
    * geospatial地理位置
    * 可以推算地理位置信息，两地之间距离，方圆几里的人
    * 规则 : 两极无法直接添加 一般会直接下载数据，java一次全部导入
    * 参数 key 经度 纬度  名称
    *
    * geoadd china:city 116.40 39.90 beijing 添加到key 经纬度，地点
    * geoadd china:city 121.40 31.923 shanghai 添加到key
    * geopos china:city beijing 得到经纬度
    * geodist 两地之间的距离 单位 m km mi ft
    * georadius china:city 110 30 500 km [withdist 显示到中心的距离| withcorrd 列出经纬度]某 经纬度 半径（单位）之内的所有东西 可以指定数量
    * georadiusbymember 以key为中心
    * geohash 返回hash字符  11个长度
    *
    * 底层是Zset实现的
    *
    *
    *hyperloglog 基数统计
    * 可以接受误差
    *
    * pfadd mykey a b c d e f g h
    * pfcount mykey 计数
    * pfmerge mukey1 mykey mykey2 合并 到 新的mykey1
    *
    * bitmap 位存储
    * 统计两种信息 是，否 就有两种结果 0 1
    *
    * setbit mysign 0 1
    * setbit mysign 1 1
    * setbit mysign 2 0 .......
    *
    * gtebit mysign 2 查看0 or 1
    * bitcount mysign 统计记录
    *
    *
    * redis事务
    * mysql ACID原则
    * redis事务本质 一些命令的集合 aof rdb
    * redis单条命令保证原子性
    * 但是事务不保证原子性
    * 一次性，顺序性，排他性（不允许呗其他东西干扰）
    * redis事务没有隔离级概念
    *
    * redis事务 -> 开启multi 入队 执行exec
    * multi
    * set k1 vi
    * set k2 v2
    * get k2
    * set k3 v2
    * exec
    *
    * discard 取消事务
    * 代码有问题事务中所有命令都不会运行
    * getset k1 //
    *
    * 语法性异常其他命令正常运行
    * 比如 除零 给一个不存在的值incr
    *
    *
    * 监控watch
    * 悲观锁 什么时候都会出问题 无论做什么都会加锁
    *
    * 乐观锁 什么时候都不会出现问题 所以不会加锁 更新的时候判断一下是不是有人修改过
    *
    * redis的监视
    *
    * set k1 100
    * set k2 0
    * watch k1
    * //正常的
    * multi
    * decrby k1 20
    * incrby k2 20
    * exec
    *
    * //有问题的
    * 当在watch的时候
    * watch的值被其他的线程修改后，事务就会执行失败
    *
    * unwatch 取消监视
    *
    *
    *
    *
    *
    *
    * */
}
