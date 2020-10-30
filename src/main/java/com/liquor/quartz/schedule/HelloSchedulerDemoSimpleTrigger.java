package com.liquor.quartz.schedule;

import com.liquor.quartz.job.HelloJobSimpleTrigger;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author Liquor.Haung
 * @Date 2020/10/30 11:39
 * To change this template use File | Settings | File Templates.
 */
public class HelloSchedulerDemoSimpleTrigger {

    public static void main(String[] args) throws Exception {
        // 1：从工厂中获取任务调度的实例
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

        // 定义日期
        Date startDate = new Date();
        // 启动任务，任务在当前时间3秒后执行
        startDate.setTime(startDate.getTime()+3000);

        // 2：定义一个任务调度实例，将该实例与HelloJob绑定，任务类需要实现Job接口
        JobDetail job = JobBuilder.newJob(HelloJobSimpleTrigger.class)
                .withIdentity("job1", "group1") // 定义该实例唯一标识
                .build();

        // 3：定义触发器 ，马上执行, 然后每5秒重复执行一次
//        Trigger trigger = TriggerBuilder.newTrigger()
//                .withIdentity("trigger1", "group1") // 定义该实例唯一标识
//                .startAt(startDate)
//                .build();

        // 3：定义触发器 ，马上执行, 然后每5秒重复执行一次
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("trigger1", "group1") // 定义该实例唯一标识
                .startAt(startDate)
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().repeatSecondlyForever(5)
                        .withRepeatCount(2)) // 每5秒执行一次，连续执行3次后停止，默认值是0
                .build();

        // 4：使用触发器调度任务的执行
        scheduler.scheduleJob(job, trigger);

        // 5：开启
        scheduler.start();
        // 关闭
        // scheduler.shutdown();
    }
}
