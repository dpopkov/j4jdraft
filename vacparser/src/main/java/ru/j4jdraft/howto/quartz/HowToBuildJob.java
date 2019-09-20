package ru.j4jdraft.howto.quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

public class HowToBuildJob {
    public static void main(String[] args) throws SchedulerException {
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();
        scheduler.start();

        JobDetail jobDetail = JobBuilder.newJob(SimpleJob.class)
                .withIdentity("myJob", "group1")
                .build();
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("myTrigger", "group1")
                .startNow()
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .withIntervalInSeconds(10)
                        .repeatForever())
                .build();
        scheduler.scheduleJob(jobDetail, trigger);
    }
}
