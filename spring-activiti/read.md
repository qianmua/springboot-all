
# Activiti 工作流引擎

    共做流程抽取，使用 BPM 建模语言定义， 实现系统流程管理

## BPMN 业务流程符号

    使用符号表示流程中的业务

## 流程建模工具 (xml文件)

    .bpmn 画好后部署到数据库中

## 流程实例

    表示一次业务流程的运行

## 待办任务

    通过activiti查询，不用去编写sql 查询

## 办理任务

    走流程

## 流程结束

    任务办理后没有下一个任务节点，流程实例就完了


# activiti db

    25张表支持
    数据库 ， h2 mysql oracle postgre db2 mssql 


## 默认配置文件,创建activi
    
    activiti.cfg.xml


## 表结构

    act 开头
    ge          通用，流程定义与流程资源
    hi          历史  历史实例，历史附件，历史说明信息，历史细节信息，历史实例
    procpdef
    re          流程定义内容  部署单元信息，模型信息，已部署定义
    ru          运行时操作表  时间，执行流程 用户关系 作业 任务 变量


## 服务

    RepositoryService  部署相关
    RuntimeService  运行时
    TaskService 
    ManagementService
    HistoryService
    FormService (新版删除)
    IdentityService (新版删除)



# 事件 ， 活动  ， 网关 ， 流向

    事件 : 开始事件 中间 ， 结束事件

    活动： 可以是任务，或者时处理的子程序；
        类型：用户任务， 服务任务，子流程

    网关处理决策
    分为： 
    排他x,有一个true 执行第一个 没有报错，条件顺序流结合，条件不满足 执行默认 ，
    并行+ ， 拆分的等待所有执行完 
    包容+ ， 多个执行
    综合 ，  
    事件  为中间捕获事件设置的，流程执行到事件网关后，处于等待状态，等待事件抛出事件才能转为活动状态

    流向FLOW： 连接两个流成节点的连接 