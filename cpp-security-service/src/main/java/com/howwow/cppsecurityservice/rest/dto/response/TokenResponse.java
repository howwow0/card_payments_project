package com.howwow.cppsecurityservice.rest.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Токен авторизации")
public record TokenResponse(
        String token
) {
}
