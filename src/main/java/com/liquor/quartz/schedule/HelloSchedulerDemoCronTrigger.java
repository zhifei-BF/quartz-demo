package com.liquor.quartz.schedule;

import com.liquor.quartz.job.HelloJobCronTrigger;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author Liquor.Haung
 * @Date 2020/10/30 11:53
 * To change this template use File | Settings | File Templates.
 */
public class HelloSchedulerDemoCronTrigger {

    public static void main(String[] args) throws Exception {
        // 1：从工厂中获取任务调度的实例
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

        // 2：定义一个任务调度实例，将该实例与HelloJob绑定，任务类需要实现Job接口
        JobDetail job = JobBuilder.newJob(HelloJobCronTrigger.class)

                .withIdentity("job1", "group1") // 定义该实例唯一标识
                .build();

        // 3：定义触发器 ，马上执行, 然后每5秒重复执行一次
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("trigger1", "group1") // 定义该实例唯一标识
                .withSchedule(CronScheduleBuilder.cronSchedule("0/5 * * 6 4 ?"))// 定义表达式
                .build();

        // 4：使用触发器调度任务的执行
        Date scheduleDate = scheduler.scheduleJob(job, trigger);
        System.out.println("调度器开始的时间是：" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(scheduleDate));

        // 5：开启
        scheduler.start();
        // 关闭
        // scheduler.shutdown();
    }
}
