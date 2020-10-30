package com.liquor.quartz.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author Liquor.Haung
 * @Date 2020/10/30 13:44
 * To change this template use File | Settings | File Templates.
 */
// 定义任务类
public class HelloJobListener implements Job {
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        // 定义时间
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = dateFormat.format(date);

        // 定义工作任务内容
        System.out.println("进行数据库备份操作。当前任务执行的时间："+dateString);
    }
}
