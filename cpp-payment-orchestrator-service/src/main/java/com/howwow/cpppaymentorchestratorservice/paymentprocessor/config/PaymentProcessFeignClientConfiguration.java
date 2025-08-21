package com.howwow.cpppaymentorchestratorservice.paymentprocessor.config;

import feign.Logger;
import feign.Request;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;

import java.util.concurrent.TimeUnit;

@Slf4j
public class PaymentProcessFeignClientConfiguration {

    @Bean
    public Logger.Level paymentProcessFeignLoggerLevel() {
        return Logger.Level.BASIC;
    }

    @Bean
    public Request.Options paymentProcessOptions() {
        return new Request.Options(
                1, TimeUnit.SECONDS,
                500, TimeUnit.MILLISECONDS,
                true
        );
    }
}