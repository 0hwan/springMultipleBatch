package com.test.example.model;

/**
 * Created by yhahn@cyworld.biz on 2017. 2. 27..
 */
public interface MessageCode {
    public static final int STATUS_SUCCESSED              = 100;
    public static final int STATUS_SUCCESSED_BUT_NOLIST   = 110;
    public static final int STATUS_FAILED                 = 500;
    public static final int STATUS_FAILED_JOB_ISNULL      = 510;
    public static final int STATUS_FAILED_UPDATE_PROGRESS = 520;
    public static final int STATUS_FAILED_UPDATE_FINISH   = 530;

    public static final int TYPE_LIST_JOB                  = 1000;
    public static final int TYPE_ALLOCATED_JOB             = 1010;
    public static final int TYPE_PROGRESS_JOB              = 2010;
    public static final int TYPE_FINISHED_JOB              = 2100;
    public static final int TYPE_FINISHED_BUT_FAILED_JOB   = 2110;
}
