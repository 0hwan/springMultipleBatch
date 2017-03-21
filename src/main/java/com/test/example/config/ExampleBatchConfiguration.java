package com.test.example.config;

import com.test.example.job.*;
import com.test.example.model.MediaContentType;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.configuration.BatchConfigurationException;
import org.springframework.batch.core.configuration.annotation.BatchConfigurer;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.explore.support.MapJobExplorerFactoryBean;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.MapJobRepositoryFactoryBean;
import org.springframework.batch.support.transaction.ResourcelessTransactionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ConcurrentTaskExecutor;
import org.springframework.transaction.PlatformTransactionManager;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

/**
 * Created by yhahn@cyworld.biz on 2017. 2. 16..
 */
@Configuration
@EnableBatchProcessing
@Profile("batch")
public class ExampleBatchConfiguration implements Serializable {
    Logger logger = Logger.getLogger(ExampleBatchConfiguration.class.getName());

    private static final int THREAD_POOL_SIZE = 4;

    @Autowired
    private JobBuilderFactory jobs;

    @Autowired
    @Qualifier("jobRepository")
    private JobRepository jobRepository;


    @Bean
    public BatchConfigurer batchConfigurer() {
        BatchConfigurer configurer = new BatchConfigurer() {
            private PlatformTransactionManager transactionManager;
            private JobRepository jobRepository;
            private JobLauncher jobLauncher;
            private JobExplorer jobExplorer;

            @Override
            public PlatformTransactionManager getTransactionManager() throws Exception {
                return transactionManager;
            }

            @Override
            public JobRepository getJobRepository() throws Exception {
                return jobRepository;
            }

            @Override
            public JobLauncher getJobLauncher() throws Exception {
                return jobLauncher;
            }

            @Override
            public JobExplorer getJobExplorer() throws Exception {
                return jobExplorer;
            }

            @PostConstruct
            public void initialize() {
                if (this.transactionManager == null) {
                    this.transactionManager = new ResourcelessTransactionManager();
                }
                try {
                    MapJobRepositoryFactoryBean jrf = new MapJobRepositoryFactoryBean(this.transactionManager);
                    jrf.afterPropertiesSet();
                    this.jobRepository = jrf.getObject();

                    MapJobExplorerFactoryBean jef = new MapJobExplorerFactoryBean(jrf);
                    jef.afterPropertiesSet();
                    this.jobExplorer = jef.getObject();

                    SimpleJobLauncher jobLauncher = new SimpleJobLauncher();
                    jobLauncher.setJobRepository(jobRepository);
                    jobLauncher.afterPropertiesSet();
                    this.jobLauncher = jobLauncher;
                } catch (Exception e) {
                    throw new BatchConfigurationException(e);
                }
            }
        };
        return configurer;
    }

    @Bean
    public JobLauncher jobLauncher() {
        final SimpleJobLauncher jobLauncher = new SimpleJobLauncher();
        jobLauncher.setJobRepository(jobRepository);
//        final SimpleAsyncTaskExecutor simpleAsyncTaskExecutor = new SimpleAsyncTaskExecutor();
//        jobLauncher.setTaskExecutor(simpleAsyncTaskExecutor);
//        final ConcurrentTaskExecutor concurrentTaskExecutor = new ConcurrentTaskExecutor();
        jobLauncher.setTaskExecutor(taskExecutor());
        return jobLauncher;
    }

    @Bean
    public TaskExecutor taskExecutor() {
        return new ConcurrentTaskExecutor(Executors.newFixedThreadPool(THREAD_POOL_SIZE));
    }

    @Bean(name="shortContentJob")
    public Job shortContentJob() {
        return jobs
                .get(MediaContentType.SHORT_CONTENT_JOB_NAME)
                .incrementer(new RunIdIncrementer())
                .listener(getExampleJobListener())
                .start(getFirstStepJob()).on(ExitStatus.FAILED.getExitCode()).to(getLastStepJob())
                .from(getFirstStepJob()).next(getMiddleStepJob()).on(ExitStatus.FAILED.getExitCode()).to(getLastStepJob())
                .from(getMiddleStepJob()).next(getLastStepJob()).on(ExitStatus.FAILED.getExitCode()).to(getLastStepJob())
                .end()
                .build();
    }

    @Bean(name="normalContentJob")
    public Job normalContentJob() {
        return jobs
                .get(MediaContentType.NORMAL_CONTENT_JOB_NAME)
                .incrementer(new RunIdIncrementer())
                .listener(getExampleJobListener())
                .start(getFirstStepJob()).on(ExitStatus.FAILED.getExitCode()).to(getLastStepJob())
                .from(getFirstStepJob()).next(getMiddleStepJob()).on(ExitStatus.FAILED.getExitCode()).to(getLastStepJob())
                .from(getMiddleStepJob()).next(getLastStepJob()).on(ExitStatus.FAILED.getExitCode()).to(getLastStepJob())
                .end()
                .build();
    }

    @Bean(name="longContentJob")
    public Job longContentJob() {
        return jobs
                .get(MediaContentType.LONG_CONTENT_JOB_NAME)
                .incrementer(new RunIdIncrementer())
                .listener(getExampleJobListener())
                .start(getFirstStepJob()).on(ExitStatus.FAILED.getExitCode()).to(getLastStepJob())
                .from(getFirstStepJob()).next(getMiddleStepJob()).on(ExitStatus.FAILED.getExitCode()).to(getLastStepJob())
                .from(getMiddleStepJob()).next(getLastStepJob()).on(ExitStatus.FAILED.getExitCode()).to(getLastStepJob())
                .end()
                .build();
    }

    @Bean
    protected FirstStepJob getFirstStepJob() {
        logger.info("FirstStepJob");
        return new FirstStepJob();

    }

    @Bean
    protected MiddleStepJob getMiddleStepJob() {
        logger.info("MiddleStepJob");
        return new MiddleStepJob();
    }

    @Bean
    protected LastStepJob getLastStepJob() {
        logger.info("LastStepJob");
        return new LastStepJob();
    }


    @Bean
    protected ExampleJobListener getExampleJobListener() {
        logger.info("getTranscodingListener");
        return new ExampleJobListener();

    }
}
