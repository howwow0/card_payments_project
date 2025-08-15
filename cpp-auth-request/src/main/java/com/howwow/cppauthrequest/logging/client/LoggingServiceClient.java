package com.howwow.cppauthrequest.logging.client;

import com.howwow.cppauthrequest.logging.config.LoggingFeignClientConfiguration;
import com.howwow.cppauthrequest.logging.dto.LogDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        name = "logging-service",
        url = "${logging-service.service.url}",
        configuration = LoggingFeignClientConfiguration.class
)
public interface LoggingServiceClient {

    @PostMapping("api/v1/logging/log")
    void createLog(@RequestBody LogDto logDto);
}
