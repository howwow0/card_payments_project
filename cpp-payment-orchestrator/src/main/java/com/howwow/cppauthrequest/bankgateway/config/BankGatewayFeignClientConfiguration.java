package com.howwow.cppauthrequest.bankgateway.config;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.howwow.cppcarddecryptionstarter.security.CardAuthToken;
import feign.Logger;
import feign.Request;
import feign.RequestInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.concurrent.TimeUnit;

@Configuration
@Slf4j
public class BankGatewayFeignClientConfiguration {

    @Bean
    public Logger.Level bankGatewayFeignLoggerLevel() {
        return Logger.Level.BASIC;
    }

    @Bean
    public Request.Options bankGatewayOptions() {
        return new Request.Options(
                1, TimeUnit.SECONDS,
                500, TimeUnit.MILLISECONDS,
                true
        );
    }

    @Bean
    public RequestInterceptor authTokenInterceptor() {
        return requestTemplate -> {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if (auth instanceof CardAuthToken authToken) {
                String token = "Bearer " + ((DecodedJWT) authToken.getCredentials()).getToken();
                requestTemplate.header("Authorization", token);
            } else {
                log.warn("Feign запрос без токена, auth: {}", auth);
            }
        };
    }
}