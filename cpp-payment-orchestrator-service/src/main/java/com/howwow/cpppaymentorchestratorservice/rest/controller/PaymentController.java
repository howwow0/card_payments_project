package com.howwow.cpppaymentorchestratorservice.rest.controller;

import com.howwow.cpppaymentorchestratorservice.business.service.PaymentSagaService;
import com.howwow.cpppaymentorchestratorservice.rest.dto.request.PaymentAuthorizationRequest;
import com.howwow.cpppaymentorchestratorservice.rest.dto.response.PaymentAuthorizationResponse;
import com.howwow.cpppaymentorchestratorservice.rest.exception.AbstractApiException;
import com.howwow.cpppaymentorchestratorservice.rest.exception.ValidationErrorException;
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
@RequestMapping("payment")
@RequiredArgsConstructor
@Tag(name = "Платеж", description = "Эндпоинт обработки платежа")
public class PaymentController {

    private final PaymentSagaService paymentSagaService;

    @Operation(
            summary = "Обработка платежа",
            description = "Эндпоинт для создания платежа"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Сага завершена, платеж завершен",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = PaymentAuthorizationResponse.class))),
            @ApiResponse(responseCode = "400", description = "Ошибка валидации входных данных",
                    content = @Content(schema = @Schema(implementation = ValidationErrorException.class))),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
                    content = @Content(schema = @Schema(implementation = AbstractApiException.class)))
    })
    @PostMapping("/authorize")
    public ResponseEntity<PaymentAuthorizationResponse> authorize(@RequestBody @Valid PaymentAuthorizationRequest paymentAuthorizationRequest) {
        return ResponseEntity.ok(paymentSagaService.executePaymentSaga(paymentAuthorizationRequest));
    }
}
