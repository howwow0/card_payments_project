package com.howwow.cppsecurityservice.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Security API",
                version = "1.0",
                description = "API для управления токенами платежа"
        )
)
public class OpenApiConfig {
}
