package com.liquor.quartz.schedule;

import com.liquor.quartz.job.HelloJob;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author Liquor.Haung
 * @Date 2020/10/30 13:17
 * To change this template use File | Settings | File Templates.
 */
public class ScheduleDemo {
    public static void main(String[] args) throws SchedulerException, InterruptedException {
        //从工厂中获取任务调度的实例
        Scheduler defaultScheduler = StdSchedulerFactory.getDefaultScheduler();
        //定义一个任务调度实例，将该实例与HelloJob绑定，任务类需要实现Job接口
        JobDetail job = JobBuilder.newJob(HelloJob.class)
                .withIdentity("job1", "group1") //定义该实例的唯一标识
                .usingJobData("message","打印日志")
                .build();
        System.out.println("name:" + job.getKey().getName());
        System.out.println("group:" + job.getKey().getGroup());
        System.out.println("jobClass:" + job.getJobClass().getName());
        //定义触发器，马上执行，然后每5秒重复执行一次
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("trigger1", "group1") //定义该实例的唯一标识
                .startNow() //马上执行
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().repeatSecondlyForever(5)) //设置每5秒执行一次
                .usingJobData("message","simple触发器")
                .build();

        //使用调度器将任务调度和触发器进行关联
        defaultScheduler.scheduleJob(job,trigger);

        //开启
        Thread.sleep(2000L);
        defaultScheduler.start();


        //关闭
//        defaultScheduler.shutdown();
    }
}
