package com.test.example.actuator.health;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

/**
 * Created by yhahn@cyworld.biz on 2017. 3. 2..
 */
@Component
public class HealthCheck implements HealthIndicator {
    private static final Logger logger = LoggerFactory.getLogger(HealthCheck.class);
    /*

    private MetricsEndpoint metricsEndpoint;

    @Autowired
    private void setMetricsEndpoint(MetricsEndpoint metricsEndpoint) {
        this.metricsEndpoint = metricsEndpoint;
    }

    @Override
    public Health health() {
        long uptime = (Long) metricsEndpoint.invoke().get("uptime");

        // logic with uptime
        return Health.up().build();
    }

    public void healthInfo() {
        double uptime = (double) metricsEndpoint.invoke().get("systemload.average");
        logger.info("healthInfo uptime : "+uptime);
        int processor = (int) metricsEndpoint.invoke().get("processors");
        logger.info("healthInfo processor : "+processor);
        long mem = (long) metricsEndpoint.invoke().get("mem");
        long memFree = (long) metricsEndpoint.invoke().get("mem.free");
        logger.info("healthInfo memory : "+memFree + " / "+mem);
    }
    */
    @Override
    public Health health() {
        int errorCode = check(); // perform some specific health check
        if (errorCode != 0) {
            return Health.down()
                    .withDetail("Error Code", errorCode).build();
        }
        return Health.up().build();
    }

    public int check() {
        // Your logic to check health
        return 0;
    }
}
