package com.test.example.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobInterruptedException;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.MetricsEndpoint;
import org.springframework.core.env.Environment;


/**
 * Created by yhahn@cyworld.biz on 2017. 2. 16..
 */
public class MiddleStepJob implements Step {
    private static final Logger logger = LoggerFactory.getLogger(MiddleStepJob.class);
    private static final String STEP_NAME = "middle-step";

    @Autowired
    private Environment env;

    @Autowired
    private MetricsEndpoint metricsEndpoint;

    @Override
    public String getName() {
        return STEP_NAME;
    }

    @Override
    public boolean isAllowStartIfComplete() {
        return false;
    }

    @Override
    public int getStartLimit() {
        return 1;
    }

    @Override
    public void execute(StepExecution stepExecution) throws JobInterruptedException {
        stepExecution.setExitStatus(ExitStatus.COMPLETED);
    }

}
