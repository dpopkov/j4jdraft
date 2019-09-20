package ru.j4jdraft.howto.quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

public class HowToBuildJobWithCronTrigger {
    public static void main(String[] args) throws SchedulerException {
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();
        scheduler.start();

        JobDetail jobDetail = JobBuilder.newJob(SimpleJob.class)
                .withIdentity("myJob", "group1")
                .build();
        CronTrigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("trigger1", "group1")
                .withSchedule(CronScheduleBuilder.cronSchedule("0/12 * 18-19 * * ?"))
                .forJob("myJob", "group1")
                .build();
        scheduler.scheduleJob(jobDetail, trigger);
    }
}
