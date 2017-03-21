package com.test.example.job;

import org.springframework.batch.core.JobExecution;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by yhahn@cyworld.biz on 2017. 3. 9..
 */
@Component
@Scope("singleton")
public class RunningExecutionTracker {
    private Map<String,Date> runningExecutions = new ConcurrentHashMap<String,Date>();

    public void addRunningExecution(String jobName, Date date){
        runningExecutions.put(jobName, date);
    }

    public void removeRunningExecution(String jobName){
        runningExecutions.remove(jobName);
    }

    public boolean isRunnning(String jobName) {
        Date date = runningExecutions.get(jobName);
        if(date != null) {
            return true;
        }
        else {
            return false;
        }
    }


//    public Set<Long> getAllRunningExecutionIds(){
//        return new HashSet<Long>(runningExecutions.keySet());
//    }

//    public Set<Long> getRunningExecutionIdsForJobName(String jobName){
//        Set<Long> runningExecutionIds = new HashSet<Long>();
//        for (Map.Entry<Long,String> entry:runningExecutions.entrySet()){
//            if (entry.getValue().equals(jobName)){
//                runningExecutionIds.add(entry.getKey());
//            }
//        }
//        return runningExecutionIds;
//    }
}
