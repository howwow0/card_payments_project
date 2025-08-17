package com.howwow.cpppaymentorchestratorservice.logging.config;

import feign.Request;
import feign.Retryer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
public class LoggingFeignClientConfiguration {

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