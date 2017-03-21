package com.test.example.model;

/**
 * Created by yhahn@cyworld.biz on 2017. 2. 28..
 */
public interface MediaContentType {
    public static final String DIRECT_CONTENT  = "s";
    public static final String SHORT_CONTENT   = "S";
    public static final String NORMAL_CONTENT  = "N";
    public static final String LONG_CONTENT    = "L";

    public static final String SHORT_CONTENT_JOB_NAME   = "transcoding-short-content";
    public static final String NORMAL_CONTENT_JOB_NAME  = "transcoding-normal-content";
    public static final String LONG_CONTENT_JOB_NAME    = "transcoding-long-content";
}
