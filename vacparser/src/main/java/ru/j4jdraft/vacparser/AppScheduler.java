package ru.j4jdraft.vacparser;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

/**
 * Запускает обработку согласно расписанию.
 */
public class AppScheduler {
    private static final String JOB_NAME = "vacparserJob";
    private static final String TRIGGER_NAME = "vacparserTrigger";
    private static final String GROUP = "vacparserGroup";

    private Job job;
    private String siteUrl;
    private DbConnector connector;

    public AppScheduler(Job job, String siteUrl, DbConnector connector) {
        this.job = job;
        this.siteUrl = siteUrl;
        this.connector = connector;
    }

    public void schedule(String cronExpression) throws SchedulerException {
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();
        scheduler.start();
        JobDataMap dataMap = new JobDataMap();
        dataMap.put("dbConnector", connector);
        dataMap.put("siteUrl", siteUrl);
        JobDetail jobDetail = JobBuilder.newJob(job.getClass())
                .withIdentity(JOB_NAME, GROUP)
                .setJobData(dataMap)
                .build();
        CronTrigger trigger = TriggerBuilder.newTrigger()
                .withIdentity(TRIGGER_NAME, GROUP)
                .withSchedule(CronScheduleBuilder.cronSchedule(cronExpression))
                .forJob(JOB_NAME, GROUP)
                .build();
        scheduler.scheduleJob(jobDetail, trigger);
    }
}
