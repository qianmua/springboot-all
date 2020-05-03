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
    * */
}
