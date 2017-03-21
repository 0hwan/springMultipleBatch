package com.test.example.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;


/**
 * Created by yhahn@cyworld.biz on 2017. 2. 16..
 */
public class FirstStepJob implements Step {
    private static final Logger logger = LoggerFactory.getLogger(FirstStepJob.class);
    private static final String STEP_NAME = "first-step";
    
    @Autowired
    private Environment env;

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

    }
}
