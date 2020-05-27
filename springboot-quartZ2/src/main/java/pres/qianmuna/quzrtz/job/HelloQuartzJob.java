package pres.qianmuna.quzrtz.job;

import org.quartz.*;

import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 *
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/5/27
 * @time 12:23
 */
@DisallowConcurrentExecution
public class HelloQuartzJob implements Job {

    /**
     * 定时任务 执行
     *
     * 工作类的具体实现
     *
     * 工作的描述
     *
     * 要执行的 某件事
     * @param jobExecutionContext 任务详情 上下文
     * @throws JobExecutionException
     */
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {


        // 创建 工作详情
        JobDetail detail = jobExecutionContext.getJobDetail();

        // 得到工作 名称
        // 任务名
        String name = detail.getKey().getName();

        //工作 组
        String group = detail.getKey().getGroup();

        //任务中的数据
        String data = detail.getJobDataMap().getString("data");

        System.out.println("//------------------------------------------//");
        System.out.println("------->" + name);
        System.out.println("------->" + group);
        System.out.println("------->" + data);

        System.out.println( new Date());
        System.out.println("//------------------------------------------//");

    }
}
