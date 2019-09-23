package ru.j4jdraft.vacparser;

import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    private static final String DEFAULT_PROP_NAME = "vacparser_app.properties";
    private static final Logger LOG = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        String propName = DEFAULT_PROP_NAME;
        if (args.length > 0) {
            propName = args[0];
        }
        AppSettings settings = new AppSettings(propName);
        AppScheduler appScheduler = new AppScheduler(VacanciesScraper.class, settings);
        try {
            appScheduler.start();
        } catch (SchedulerException e) {
            LOG.error("Scheduler error", e);
        }
    }
}
