package com.howwow.cppsecurityservice.rest.controller;

import com.howwow.cppsecurityservice.business.JwtService;
import com.howwow.cppsecurityservice.rest.dto.request.CardAuthRequest;
import com.howwow.cppsecurityservice.rest.dto.response.TokenResponse;
import com.howwow.cppsecurityservice.rest.exception.AbstractApiException;
import com.howwow.cppsecurityservice.rest.exception.ValidationErrorException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("security")
@RequiredArgsConstructor
@Tag(name = "JWT токены", description = "Генерация токенов для карты")
public class TokenController {
    private final JwtService jwtService;

    @PostMapping("/generate")
    @Operation(summary = "Генерирует JWT-токен", description = "Принимает данные карты, шифрует их и возвращает токен")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешная генерация токена",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = TokenResponse.class))),
            @ApiResponse(responseCode = "400", description = "Ошибка валидации входных данных",
                    content = @Content(schema = @Schema(implementation = ValidationErrorException.class))),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
                    content = @Content(schema = @Schema(implementation = AbstractApiException.class)))
    })
    public ResponseEntity<TokenResponse> generateToken(@RequestBody @Valid CardAuthRequest cardAuthRequest) {
        return ResponseEntity.ok(jwtService.generateToken(cardAuthRequest));
    }
}
