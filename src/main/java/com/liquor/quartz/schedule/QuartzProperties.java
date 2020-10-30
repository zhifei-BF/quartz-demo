package com.liquor.quartz.schedule;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Properties;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author Liquor.Haung
 * @Date 2020/10/30 13:40
 * To change this template use File | Settings | File Templates.
 */
public class QuartzProperties {

    public static void main(String[] args) {
        // 创建工厂实例
        StdSchedulerFactory factory = new StdSchedulerFactory();

        // 创建配置工厂的属性对象
        Properties props = new Properties();
        props.put(StdSchedulerFactory.PROP_THREAD_POOL_CLASS, "org.quartz.simpl.SimpleThreadPool"); // 线程池定义
        props.put("org.quartz.threadPool.threadCount", "5"); // 默认Scheduler的线程数

        try {
            // 使用定义的属性初始化工厂
            factory.initialize(props);

            Scheduler scheduler = factory.getScheduler();

            scheduler.start();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
}
