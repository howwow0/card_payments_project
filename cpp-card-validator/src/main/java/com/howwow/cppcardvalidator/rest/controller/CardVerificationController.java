package com.howwow.cppcardvalidator.rest.controller;

import com.howwow.cppcardvalidator.rest.dto.request.CardDataRequest;
import com.howwow.cppcardvalidator.rest.dto.response.CardValidationResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/card")
@Tag(name = "Проверка карт", description = "API для валидации данных банковских карт")
public class CardVerificationController {

    @Operation(
            summary = "Проверить валидность карты",
            description = "Проверяет корректность номера карты, срока действия и CVV кода",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Карта валидна/невалидна с сообщением",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = CardValidationResponse.class),
                                    examples = {
                                            @ExampleObject(
                                                    value = "{\"valid\": true, \"message\": \"Карта успешно проверена\"}"
                                            ),
                                            @ExampleObject(
                                                    value = "{\"valid\": false, \"message\": \"Невалидные данные карты\"}"
                                            )
                                    }
                            )
                    )
            }
    )


    @PostMapping("/validate")
    public ResponseEntity<CardValidationResponse> validateCard(@RequestBody @Valid CardDataRequest ignored) {
        return ResponseEntity.ok(CardValidationResponse.success());
    }
}