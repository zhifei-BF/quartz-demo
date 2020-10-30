package com.liquor.quartz.job;

import org.quartz.*;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author Liquor.Haung
 * @Date 2020/10/30 10:55
 * To change this template use File | Settings | File Templates.
 */
//@PersistJobDataAfterExecution
public class HelloJob implements Job {
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JobKey jobKey = jobExecutionContext.getJobDetail().getKey();
        System.out.println("工作任务名称："+jobKey.getName()+";工作任务组："+jobKey.getGroup());
        System.out.println("任务类名称（带包名）："+jobExecutionContext.getJobDetail().getJobClass().getName());
        System.out.println("任务类名称："+jobExecutionContext.getJobDetail().getJobClass().getSimpleName());
        System.out.println("当前任务执行时间："+jobExecutionContext.getFireTime());
        System.out.println("下一任务执行时间："+jobExecutionContext.getNextFireTime());

        TriggerKey triggerKey = jobExecutionContext.getTrigger().getKey();
        System.out.println("触发器名称："+triggerKey.getName()+";触发器组："+triggerKey.getGroup());

        JobDataMap jobDataMap = jobExecutionContext.getJobDetail().getJobDataMap();
        String jobMessage = jobDataMap.getString("message");
        System.out.println("任务参数消息值："+jobMessage);

        JobDataMap triggerDataMap = jobExecutionContext.getTrigger().getJobDataMap();
        String triggerMessage = triggerDataMap.getString("message");
        System.out.println("触发器参数消息值："+triggerMessage);
        //定义时间
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss SSS");
        String dateString = dateFormat.format(date);
        //定义工作任务内容
        System.out.println("进行数据库备份操作。当前任务执行的时间："+dateString);
    }
}
