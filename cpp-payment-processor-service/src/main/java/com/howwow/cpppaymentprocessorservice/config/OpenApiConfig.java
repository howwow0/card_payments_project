package com.howwow.cpppaymentprocessorservice.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Payment Processor API",
                version = "1.0",
                description = "API для фиксирования финального статуса транзакции и интеграции с Kafka"
        )
)
public class OpenApiConfig {
}
