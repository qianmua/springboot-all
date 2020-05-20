package pres.qianmuna.mongodb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by IntelliJ IDEA.
 *
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/5/5
 * @time 16:49
 */
@SpringBootApplication
public class MongodbApplication {


    /*
    * mongodb
    *
    * 常用命令
    *
    * show dbs
    * 三个默认库
    *
    * use testdb //没有会动创建
    * 存储方式市 内存 / 磁盘 两部分
    * 当内存中testdb 有数据时 持久化到磁盘
    *
    * admin 用户库
    * local 本地 不会被复制
    * config 保存分片相关信息
    *
    * 删除 db.dropDatabase()
    *
    * 创建集合
    * 没有自己会创建
    *
    * db.user.insert({"test":"100" , "aaa":"11"})
    * db.user.find()
    * db.user.find({"test":"100"})
    * db.user.findOne({"test":"100"})
    * 投影查询 就是 只显示字段
    * db.user.find({"test":"100"},{test:1 , _id:0})
    *
    * 批量插入可以使用try catch
    * try{
    *   db.user.insertMany([ {},{},{}]);
    * }catch(e){
    *   print(e);
    * }
    *
    * 更新
    * db.user.update({test:"100"} , {$set:{test:NumberInt(1001)}})
    * 默认只修改一条
    *
    * //批量修改
    * db.user.update({test:"100"} , {$set:{test:NumberInt(1001)} }，{multi:true})
    *
    * //每次增加一
    * db.user.update({test:"100"} , {$inc:{test:NumberInt(1001)}})
    *
    *
    * //删除
    * db.user.remove({"user":"1001"})
    *
    * //统计
    * db.user.count()
    *
    * //分页
    * db.user.find().limit(2)
    *
    * //跳过
    * db.user.find().limit(2).skip(2)
    *
    * //排序
    * db.user.find().sort({KEY:1})  正
    * db.user.find().sort({KEY:-1}) 降
    *
    * //正则
    * db.user.find({user:/d+/})
    *
    * 比较
    * db.user.find({user:{ $gt:value }}) //gt lt gte lte ne 不加就是等于
    *
    * 包含
    * $in $nin
    * db.user.find({user:{ $in: ["111","222"]}})
    *
    * 条件
    * $and:[{},{},{}]
    * $or
    * db.user.find({} , $and:[{},{}])
    *
    *
    * 索引
    * db.user.getIndexes()
    *
    * 创建索引
    * db.user.createIndex({userId:1})
    * // 符合索引 1 ， -1 就是升降排序
    * db.user.createIndex({userId:1 , age:-1})
    * 删除
    * db.user.dropIndex({ user:1 })
    * 删除所有
    * db.user.dropIndexes()
    *
    * 执行计划查看
    * db.user.find({userId:"1001"}).explain()
    *
    * 涵盖查询 ，只查索引字段触发
    *
    *
    *
    * 安全认证
    *
    * 角色 ， 权限
    *
    * 查看所有角色
    * db.runCommand({rolesInfo:1} , showBuiltinRoles: true })
    *
    *
    * 角色
    * read
    * readWrite
    * readAnyDatabase
    * readWriteAnyDatabase
    * userAdminAnuDatabase
    *
    * dbAdmin
    * userAdmin
    * clusterAdmin
    * backup
    * restore
    * root
    *
    *
    *
    *
    *
    *
    *
    *
    *
    *
    * */

    public static void main(String[] args) {
        SpringApplication.run(MongodbApplication.class,args);
    }
}
