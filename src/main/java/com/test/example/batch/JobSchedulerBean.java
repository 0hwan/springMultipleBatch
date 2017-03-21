package com.test.example.batch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Profile("batchaaa")
public class JobSchedulerBean {
    /**
     * The Logger for this Class.
     */
    private static final Logger logger = LoggerFactory.getLogger(JobSchedulerBean.class);

//    @Autowired
//    private SimpleJobLauncher jobLauncher;

    @Scheduled(
            cron = "${batch.start.cron}")
    public void cronJob() {
        logger.info("> cronJob");

//        SpringApplication.run(TranscoderBatchConfiguration.class);
//        JobExecution execution = jobLauncher.run(TranscoderBatchConfiguration.class, null);


        logger.info("< cronJob");
    }
}
