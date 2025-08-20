package com.howwow.cpppaymentrecordingservice.rest.controller;

import com.howwow.cpppaymentrecordingservice.business.service.PaymentRecordingService;
import com.howwow.cpppaymentrecordingservice.rest.dto.response.PaymentResponse;
import com.howwow.cpppaymentrecordingservice.rest.exception.AbstractApiException;
import com.howwow.cpppaymentrecordingservice.rest.exception.ValidationErrorException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("payment")
@AllArgsConstructor
@Tag(name = "Платежи", description = "API для работы с платежами")
@Validated
public class PaymentRecordingController {


    private final PaymentRecordingService paymentRecordingService;

    @GetMapping("/{id}")
    @Operation(
            summary = "Получить платеж по ID",
            description = "Возвращает информацию о платеже по его ID"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Платеж найден",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = PaymentResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Ошибка валидации входных данных",
                    content = @Content(
                            schema = @Schema(implementation = ValidationErrorException.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Внутренняя ошибка сервера",
                    content = @Content(
                            schema = @Schema(implementation = AbstractApiException.class)
                    )
            )
    })
    public ResponseEntity<PaymentResponse> getPaymentById(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(paymentRecordingService.getPayment(id));
    }

}
