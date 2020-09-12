
# elasticsearch

    面向文档 
    
> db    es（一切皆json）

    数据库表        索引
    表               types (7.0慢慢弃用)
    行               文档
    字段              字段
    
**物理设置**

    在后台把每个索引划分成分片， 分片可以在不同服务器迁移
    
    集群默认名称 : elasticsearch
    
- 一个包含多个文档
- 一篇文档包含自文档， 就是json段 
- 自我包含 ， 文档同时包含字段和对应的值，即：key：value
- 结构灵活， 可以忽略或者动态的添加

**类型有时候也称为映射类型**

    name String text
    date....
    类型映射 (类似 自动推断)
    
    
> 索引

    db
    
    默认创建 五个分片
    
    一个分片就是一个lucene 索引
    一个包含 倒排索引 的文件目录
    
    倒排索引 结构使得es在不扫描全部文档下 就可以找到特定关键字
    
> 倒排索引

    采用lucene 倒排索引作为底层
    
    创建：
    
    将每个文档拆分成独立的词（词条 ， tokens）
    然后创建所有词条所有不重复的排序列表
    然后列出所在文档
    
    查找词条时， 按照权重（score） 取
    
    
    查找字典只找到有关文档即可
    
## 一个 es索引包含多个 lucene 倒排索引

    
    
## ik 分词器

    把一段 中文或者英文 分成一个个的词（关键字）
    
    然后进行匹配操作
    
> 中文分词器 ik分词器

    分词算法
    ik_smark    最少切分 
    ik_max_word 最细粒度划分
    
    
    
## restful 风格

    示例：
   
    不同分词器 效果：
    
```
// 自动识别词
// 没有重复词

GET _analyze
{
    "analyzer": "ik_smart",
    "text": "文本"
}

// 穷尽词库
GET _analyze
{
    "analyzer": "ik_max_word",
    "text": "文本"
}

```

> 自定义词库

    词典后缀 .dic
    
    
## restful风格操作

    一种原则和约束条件

    rest 通过不同命令操作不同的类型

    
    
> 创建索引

    put     创建
    post    创建
    post    修改
    delete  删除
    get     查找一个
    post    查找所有
    
    创建索引
    PUT /test1/type1/1
    {
        "name": "aaa",
        "age" : 111
        
    }
    
    // PUT /索引名/类型名（逐渐弃用）/文档id
    {
        请求体
    }
    
> 类型

    字符串类型
    text    keyword
    数值类型
    long integer short byte double float half float scaled
    日期类型
    date
    二进制类型
    binary
    ..
    
    
> 指定字段类型
    
    创建规则
```
PUT /test2
{
    "mappings":{
        "properties":{
            "name":{
                "type": "text" 
            },
            "age": {
                "type" : "long"
            }
        }
    }
}

```

> Get

    // 查看具体信息
    `GET test2`


**文档没有指定类型，es则会默认配置字段类型**


> 修改文档


    POST + _update
```
POST /test1/doc1/1/_update
{
    "tets":{
        "name": "aaaa"
    }
}
```


> 查找

`GET test1/docuser/_search?q=name:ddddd`

    匹配设置
    
```
GET test1/doc/_search
{
    "query":{
    // 匹配
        "match": {
            "name": "ddd"
        }
    },
    // 结果过滤 ， 按照 字段查找
    "_source": ["name" , "desc"],
    //排序
    "sort" : [
        {
            "age": {
                "order": "asc"
            }
        }
    ],
    // 分页
    "from": 0,
    "size" : 100,
    // 布尔值查询
    "bool" : {
        // must  (and)
        // should (or)
        "must":[
            {
                "match": {
                    "name": "a",
                }
            },
            {
                "match": {
                    "age": 11
                }
            }
        ]
    },
    // 过滤器
    "filter":{
        "range":{
            "age":{
                "lt" : 10
            }
        }
    }
}
```

> 过滤器
    
- gt >
- lt <
- gte >= 
- lte <= 


    区间：
    "age": {
        "gt": 1,
        "lt": 100
    } 
 
    
> 匹配多个条件

    用空格隔开
```

GET test1/doc/_search
{
    "query":{
    // 匹配 多个条件
        "match": {
            "name": "ddd aaa ccc"
        }
    }
}

```
> 精准查询

    term // 直接通过倒排索引指定词条精确查询
    
    match // 使用分词器解析 (先分析文档 ， 然后用分析结果查询)
    
    
## text keyword    

    text 可以被分词器解析
    
    keyword 不会被解析
    
        
> 高亮查询

    "query":{
        "match":{
            "name":"qm啊"
        }
    },
    "higlight":{
        // 自定义标签
        // 默认 <em>
        "pres_tags": "<p class='key' stype='color:red'>",
        "post_tags": "</p>"
        
        "fields":{
            "name": {} // 查询结果字段 <em>qm</em><em>啊</em>test
        }
    }