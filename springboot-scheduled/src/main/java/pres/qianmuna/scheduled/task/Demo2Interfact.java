package pres.qianmuna.scheduled.task;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;

/**
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/7/7  12:41
 * @description :
 */
@Configuration
@EnableScheduling
@EnableAsync
public class Demo2Interfact implements SchedulingConfigurer {

    @Bean
    public Executor taskExecutor(){
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(10);
        executor.setMaxPoolSize(100);
        executor.setQueueCapacity(10);
        executor.initialize();
        return executor;
    }


    // get select

    @Mapper
    public interface Cron{

        @Select("select * from table_demo_jpa_m2po")
        List<Map<String , Object>> queryMap();
    }

    @Resource
    private Cron cron;

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.addTriggerTask(() -> System.out.println(" run task -> " + LocalDateTime.now().toLocalTime()) ,
                 triggerContext -> {
                    List<Map<String, Object>> maps = cron.queryMap();
                    if (maps.isEmpty()){
                        System.out.println("empty");
                    }
                    // get cron
                    return new CronTrigger("0/5 * * * * ?").nextExecutionTime(triggerContext);
                });
    }
}
