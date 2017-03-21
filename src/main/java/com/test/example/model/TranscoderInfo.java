package com.test.example.model;

import lombok.Data;

import java.util.Date;

/**
 * Created by yhahn@cyworld.biz on 2017. 3. 2..
 */
public @Data
class TranscoderInfo {
    String name;
    String serviceIp;
    int servicePort;
    double systemLoad;
    String type;
    Date updatedTime;
}
