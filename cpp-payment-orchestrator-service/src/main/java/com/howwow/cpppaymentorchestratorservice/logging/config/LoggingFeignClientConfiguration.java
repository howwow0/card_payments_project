package com.howwow.cpppaymentorchestratorservice.logging.config;

import feign.Logger;
import feign.Request;
import feign.Retryer;
import org.springframework.context.annotation.Bean;

import java.util.concurrent.TimeUnit;

public class LoggingFeignClientConfiguration {

    @Bean
    public Logger.Level loggingFeignLoggerLevel() {
        return Logger.Level.BASIC;
    }

    @Bean
    public Request.Options loggingOptions() {
        return new Request.Options(
                1, TimeUnit.SECONDS,
                500, TimeUnit.MILLISECONDS,
                true
        );
    }

    @Bean
    public Retryer retryer() {
        return new Retryer.Default(
                100,
                TimeUnit.SECONDS.toMillis(1),
                2
        );
    }
}