//package com.felix.springbootdemo.config;
//
//import com.felix.springbootdemo.model.QuartzEntity;
//import lombok.extern.slf4j.Slf4j;
//import org.quartz.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.ApplicationArguments;
//import org.springframework.boot.ApplicationRunner;
//import org.springframework.stereotype.Component;
//
//@Component
//@Slf4j
//public class TaskRunner implements ApplicationRunner {
//
//    @Autowired
//    private Scheduler scheduler;
//
//    @SuppressWarnings({"rawtypes", "unchecked"})
//    @Override
//    public void run(ApplicationArguments var) throws Exception {
//        log.info("初始化测试任务");
//        QuartzEntity quartz = new QuartzEntity();
//        quartz.setJobName("test01")
//                .setJobGroup("test")
//                .setDescription("测试任务")
//                .setJobClassName("com.felix.springbootdemo.job.Test")
//                .setCronExpression("0/20 * * * * ?");
//        Class cls = Class.forName(quartz.getJobClassName());
//        cls.newInstance();
//        //构建job信息
//        JobDetail job = JobBuilder.newJob(cls).withIdentity(quartz.getJobName(),
//                quartz.getJobGroup())
//                .withDescription(quartz.getDescription()).build();
//        // 触发时间点
//        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(quartz.getCronExpression());
//        Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger" + quartz.getJobName(), quartz.getJobGroup())
//                .startNow().withSchedule(cronScheduleBuilder).build();
//        //交由Scheduler安排触发
//        //scheduler.scheduleJob(job, trigger);
//    }
//
//}