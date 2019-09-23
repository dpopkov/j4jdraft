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

    private final Class <? extends Job> jobClass;
    private final AppSettings appSettings;

    public AppScheduler(Class <? extends Job> jobClass, AppSettings appSettings) {
        this.jobClass = jobClass;
        this.appSettings = appSettings;
    }

    public void start() throws SchedulerException {
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler quartzScheduler = schedulerFactory.getScheduler();
        quartzScheduler.start();
        JobDataMap dataMap = new JobDataMap();
        dataMap.put("appSettings", appSettings);
        JobDetail jobDetail = JobBuilder.newJob(jobClass)
                .withIdentity(JOB_NAME, GROUP)
                .usingJobData(dataMap)
                .build();
        CronTrigger trigger = TriggerBuilder.newTrigger()
                .withIdentity(TRIGGER_NAME, GROUP)
                .withSchedule(CronScheduleBuilder.cronSchedule(appSettings.cronTime()))
                .forJob(JOB_NAME, GROUP)
                .build();
        quartzScheduler.scheduleJob(jobDetail, trigger);
    }
}