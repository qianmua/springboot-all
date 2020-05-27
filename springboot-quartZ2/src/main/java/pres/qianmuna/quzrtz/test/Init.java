package pres.qianmuna.quzrtz.test;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import pres.qianmuna.quzrtz.job.HelloQuartzJob;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/5/27  12:55
 * @description :
 */
public class Init {

    /**
     * 启动时 会 自动 调用 一次
     *
     * 是异步的哦
     *
     *
     * job 并发 ？
     *
     * 可能 会触发 重叠
     *
     * 并发情况下可能出现重叠 任务
     *
     * 注解
     * @DisallowConcurrentExecution 禁止并发执行
     *
     *
     * @param args
     * @throws SchedulerException
     */

    public static void main(String[] args) throws SchedulerException {
        cron();
    }

    private static void cron() throws SchedulerException{
        // 创建 调度器
        // 核心 组件
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        //触发器 触发条件
        Trigger trigger = TriggerBuilder.newTrigger()
                // 绑定 name , group
                .withIdentity("t1" , "g1")
                // cron 表达式
                .withSchedule( CronScheduleBuilder.cronSchedule("0/2 * * * * ?"))
                .build();
        // 创建 job // 就是 真正的 工作
        // 一个 job 一个 trig 关联
        JobDetail jobDetail = JobBuilder.newJob(HelloQuartzJob.class)
                // 绑定 name ， group
                .withIdentity("j1", "g2")
                // 工作 数据
                .usingJobData("data", " test hello world")
                .build();

        // 在调度器 中 注册
        scheduler.scheduleJob(jobDetail, trigger);
        // 启动 开始 计时
        scheduler.start();
    }


    private static void sc() throws SchedulerException{
        // 创建 调度器
        // 核心 组件
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        //触发器 触发条件
        Trigger trigger = TriggerBuilder.newTrigger()
                // 绑定 name , group
                .withIdentity("t1" , "g1")
                // 加入 调度器后 直接 生效
                .startNow()
                // 定时 启动
//                .startAt()
                // 定时 执行 每2s 3次
                // 任务 规则
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(2).repeatForever())
                // 结束 规则 可以不要的
                .endAt(new GregorianCalendar( 2020 , Calendar.MAY , 29 , 13 , 30 , 50 ).getTime())
                .build();
        // 创建 job // 就是 真正的 工作
        // 一个 job 一个 trig 关联
        JobDetail jobDetail = JobBuilder.newJob(HelloQuartzJob.class)
                // 绑定 name ， group
                .withIdentity("j1", "g2")
                // 工作 数据
                .usingJobData("data", " test hello world")
                .build();

        // 在调度器 中 注册
        scheduler.scheduleJob(jobDetail, trigger);
        // 启动 开始 计时
        scheduler.start();
    }
}
