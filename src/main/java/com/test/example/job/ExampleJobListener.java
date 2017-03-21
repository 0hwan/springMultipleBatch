package com.test.example.job;

import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * Created by ddascal on 22/01/15.
 */
public class ExampleJobListener implements JobExecutionListener {
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(ExampleJobListener.class);

    @Autowired
    RunningExecutionTracker runningExecutionTracker;

    @Override
    public void beforeJob(JobExecution jobExecution) {
        String jobName = jobExecution.getJobInstance().getJobName();
        Date date = jobExecution.getJobParameters().getDate(JobProperties.KEY_OF_DATE);
        if(runningExecutionTracker.isRunnning(jobName)) {
            jobExecution.setExitStatus(ExitStatus.FAILED);
            jobExecution.stop();
            logger.info("============== beforeJob() [{}] is already running ==============", jobName);
        }
        else {
            runningExecutionTracker.addRunningExecution(jobName, date);
            logger.info("============== beforeJob() [{}] is starting ==============", jobName);
        }

    }

    @Override
    public void afterJob(JobExecution jobExecution) {
//        String jobName = jobExecution.getJobParameters().getString(JobProperties.KEY_OF_JOB_NAME);
        String jobName = jobExecution.getJobInstance().getJobName();
        if(jobName != null) {
            runningExecutionTracker.removeRunningExecution(jobName);
            logger.info("============== afterJob() [{}] ==============", jobName);
        }
        else {
            logger.info("============== afterJob() ==============");
        }
    }
}
