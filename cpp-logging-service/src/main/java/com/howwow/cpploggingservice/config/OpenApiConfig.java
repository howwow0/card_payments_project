package com.howwow.cpploggingservice.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Logging API",
                version = "1.0",
                description = "API для логирования этапов платежа"
        )
)
public class OpenApiConfig {
}
