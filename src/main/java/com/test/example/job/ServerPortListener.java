package com.test.example.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.embedded.EmbeddedServletContainerInitializedEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * Created by yhahn@cyworld.biz on 2017. 3. 6..
 */
@Component
@Profile("batch")
public class ServerPortListener {
    private static final Logger logger = LoggerFactory.getLogger(FirstStepJob.class);

    @Value("${server.port}")
    private int servicePort;

    public int getServicePort() {
        return servicePort;
    }


    @EventListener
    public void handleContextRefresh(EmbeddedServletContainerInitializedEvent event) {
        servicePort = event.getEmbeddedServletContainer().getPort();
        logger.info("<<<<<<<< handleContextRefresh port : "+servicePort);
    }
}
