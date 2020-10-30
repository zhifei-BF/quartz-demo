package com.liquor.quartz.schedule;

import com.liquor.quartz.job.HelloJobListener;
import com.liquor.quartz.listener.MyTriggerListener;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.EverythingMatcher;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author Liquor.Haung
 * @Date 2020/10/30 14:08
 * To change this template use File | Settings | File Templates.
 */
public class HelloSchedulerDemoTriggerListener {
    public static void main(String[] args) throws Exception {
        // 1：从工厂中获取任务调度的实例
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

        // 2：定义一个任务调度实例，将该实例与HelloJob绑定，任务类需要实现Job接口
        JobDetail job = JobBuilder.newJob(HelloJobListener.class)
                .withIdentity("job1", "group1") // 定义该实例唯一标识
                .build();
        // 3：定义触发器 ，马上执行, 然后每5秒重复执行一次
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("trigger1", "group1") // 定义该实例唯一标识
                .startNow()  // 马上执行
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .repeatSecondlyForever(5)) // 每5秒执行一次
                .build();

        // 4：使用触发器调度任务的执行
        scheduler.scheduleJob(job, trigger);

        // 创建并注册一个全局的Trigger Listener
        scheduler.getListenerManager().addTriggerListener(new MyTriggerListener("simpleTrigger"), EverythingMatcher.allTriggers());

        // 创建并注册一个局部的Trigger Listener
        // scheduler.getListenerManager().addTriggerListener(new MyTriggerListener("simpleTrigger"), KeyMatcher.keyEquals(TriggerKey.triggerKey("trigger1", "group1")));

        // 5：开启
        scheduler.start();
        // 关闭
        // scheduler.shutdown();
    }
}
