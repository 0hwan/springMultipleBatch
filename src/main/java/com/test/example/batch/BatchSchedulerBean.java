package com.test.example.batch;

import com.test.example.actuator.health.HealthCheck;
import com.test.example.model.MediaContentType;
import com.test.example.job.JobProperties;
import com.test.example.job.RunningExecutionTracker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.endpoint.MetricsEndpoint;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Profile("batch")
public class BatchSchedulerBean {
    /**
     * The Logger for this Class.
     */
    private static final Logger logger = LoggerFactory.getLogger(BatchSchedulerBean.class);

    @Autowired
    JobLauncher jobLauncher;

    @Autowired
    Job shortContentJob;

    @Autowired
    Job normalContentJob;

    @Autowired
    Job longContentJob;

    @Autowired
    HealthCheck healthCheck;

    @Autowired
    RunningExecutionTracker runningExecutionTracker;

    @Autowired
    private MetricsEndpoint metricsEndpoint;

    @Value("${batch.type}")
    String transcoderType;

    @Value("${batch.type.short.enabled}")
    boolean shortContentSchedulerEnabled;

    @Value("${batch.type.normal.enabled}")
    boolean normalContentSchedulerEnabled;

    @Value("${batch.type.long.enabled}")
    boolean longContentSchedulerEnabled;

    @Scheduled(fixedDelayString = "${batch.fixeddelay}")
    public void shortContentCron() {
        if(shortContentSchedulerEnabled) {
            logger.info("> shortContentCron");

            try {
                JobParametersBuilder builder = new JobParametersBuilder();
                builder.addDate(JobProperties.KEY_OF_DATE, new Date());

                if (!runningExecutionTracker.isRunnning(MediaContentType.SHORT_CONTENT_JOB_NAME)) {
                    JobExecution execution = jobLauncher.run(shortContentJob, builder.toJobParameters());

                    logger.info("=====> shortContentCron Exit Status : " + execution.getStatus());
                }

            } catch (Exception e) {
                logger.info("=====> shortContentCron Error  : " + e.getMessage());
            }
            logger.info("< shortContentCron");
        }
    }

    @Scheduled(fixedDelayString = "${batch.fixeddelay}")
    public void normalContentCron() {
        if(normalContentSchedulerEnabled) {
            logger.info("> normalContentCron");

            try {
                JobParametersBuilder builder = new JobParametersBuilder();
                builder.addDate(JobProperties.KEY_OF_DATE, new Date());

                if (!runningExecutionTracker.isRunnning(MediaContentType.NORMAL_CONTENT_JOB_NAME)) {
                    JobExecution execution = jobLauncher.run(normalContentJob, builder.toJobParameters());
                    logger.info("=====> normalContentCron Exit Status : " + execution.getStatus());
                }


            } catch (Exception e) {
                logger.info("=====> normalContentCron Error  : " + e.getMessage());
            }
            logger.info("< normalContentCron");
        }
    }


    @Scheduled(fixedDelayString = "${batch.fixeddelay}")
    public void longContentCron() {
        if(longContentSchedulerEnabled) {
            logger.info("> longContentCron");

            try {
                JobParametersBuilder builder = new JobParametersBuilder();
                builder.addDate(JobProperties.KEY_OF_DATE, new Date());

                if (!runningExecutionTracker.isRunnning(MediaContentType.LONG_CONTENT_JOB_NAME)) {
                    JobExecution execution = jobLauncher.run(longContentJob, builder.toJobParameters());
                    logger.info("=====> longContentCron Exit Status : " + execution.getStatus());
                }
            } catch (Exception e) {
                logger.info("=====> longContentCron Error  : " + e.getMessage());
            }
            logger.info("< longContentCron");
        }
    }
}
