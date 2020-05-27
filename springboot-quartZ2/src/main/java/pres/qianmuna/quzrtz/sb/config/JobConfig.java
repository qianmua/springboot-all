package pres.qianmuna.quzrtz.sb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;
import pres.qianmuna.quzrtz.sb.config.handler.JobFactory;
import pres.qianmuna.quzrtz.sb.config.job.JobQuartZ;

/**
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/5/27  14:31
 * @description :
 */
@Configuration
public class JobConfig {


    /**
     * 创建job
     *
     *
     * 底层 是通过 反射 得到 的 ，
     */
    @Bean
    public JobDetailFactoryBean jobDetailFactoryBean(){
        JobDetailFactoryBean bean = new JobDetailFactoryBean();
        // 关联 job 类
        bean.setJobClass(JobQuartZ.class);

        return bean;
    }

    /**
     * 创建 trigger
     *
     *
     */
    @Bean
    public SimpleTriggerFactoryBean simpleTriggerFactoryBean(JobDetailFactoryBean jobDetailFactoryBean){
        SimpleTriggerFactoryBean bean = new SimpleTriggerFactoryBean();
        // 关联 jobDetail
        bean.setJobDetail( jobDetailFactoryBean.getObject());

        // 任务 调度规则
        bean.setRepeatCount(2000);

        bean.setRepeatCount(10);
        return bean;
    }

    /**
     * Cron 表达式
     */
    @Bean
    public CronTriggerFactoryBean cronTriggerFactoryBean(JobDetailFactoryBean jobDetailFactoryBean){
        CronTriggerFactoryBean bean = new CronTriggerFactoryBean();

        bean.setCronExpression("0/2 * * * * ?");

        return bean;
    }


    /**
     * 创建调度器
     */
    @Bean
    public SchedulerFactoryBean schedulerFactoryBean(CronTriggerFactoryBean simpleTriggerFactoryBean , JobFactory MyJobFactory){
        SchedulerFactoryBean bean = new SchedulerFactoryBean();

        // 关联 trig
        bean.setTriggers( simpleTriggerFactoryBean.getObject());
        bean.setJobFactory(MyJobFactory);

        return bean;
    }






}
