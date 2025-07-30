package com.howwow.cppauthrequest.config;

import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayFeignClientConfig {

    @Bean
    public ErrorDecoder gatewayErrorDecoder() {
        return new GatewayFeignErrorDecoder();
    }

}
