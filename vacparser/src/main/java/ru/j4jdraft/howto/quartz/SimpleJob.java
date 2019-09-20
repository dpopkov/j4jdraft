package ru.j4jdraft.howto.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;

public class SimpleJob implements Job {
    @Override
    public void execute(JobExecutionContext context) {
        System.out.println("This is a quartz job!");
    }
}
