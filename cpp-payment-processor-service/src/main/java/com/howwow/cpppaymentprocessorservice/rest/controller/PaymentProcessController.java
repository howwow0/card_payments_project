package com.howwow.cpppaymentprocessorservice.rest.controller;

import com.howwow.cpppaymentprocessorservice.business.service.PaymentProcessService;
import com.howwow.cpppaymentprocessorservice.rest.dto.request.PaymentProcessRequest;
import com.howwow.cpppaymentprocessorservice.rest.dto.response.PaymentProcessResponse;
import com.howwow.cpppaymentprocessorservice.rest.exception.AbstractApiException;
import com.howwow.cpppaymentprocessorservice.rest.exception.ValidationErrorException;
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
@Tag(name = "PaymentProcess", description = "Эндпоинты обработки платежей")
public class PaymentProcessController {
    private final PaymentProcessService paymentProcessService;

    @Operation(
            summary = "Обработка платежа",
            description = "Эндпоинт для финальной фиксации платежа и отправки события в Kafka"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Платеж завершен, данные отправлены в Kafka",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = PaymentProcessResponse.class))),
            @ApiResponse(responseCode = "400", description = "Ошибка валидации входных данных",
                    content = @Content(schema = @Schema(implementation = ValidationErrorException.class))),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
                    content = @Content(schema = @Schema(implementation = AbstractApiException.class)))
    })
    @PostMapping("/process")
    public ResponseEntity<PaymentProcessResponse> authorize(@RequestBody @Valid PaymentProcessRequest paymentProcessRequest) {
        return ResponseEntity.ok(paymentProcessService.processPayment(paymentProcessRequest));
    }
}
