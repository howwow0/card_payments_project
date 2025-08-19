package com.howwow.cppbankgatewayservice.rest.controller;

import com.howwow.cppbankgatewayservice.business.BankService;
import com.howwow.cppbankgatewayservice.rest.dto.request.GatewayAuthorizationRequest;
import com.howwow.cppbankgatewayservice.rest.dto.response.GatewayAuthorizationResponse;
import com.howwow.cppbankgatewayservice.rest.exception.AbstractApiException;
import com.howwow.cppbankgatewayservice.rest.exception.ValidationErrorException;
import com.howwow.cppcarddecryptionstarter.carddecrypt.annotation.DecryptedCardData;
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
@RequestMapping("/bank")
@RequiredArgsConstructor
@Tag(name = "Bank Gateway API", description = "API банка - эмитента")
public class BankController {

    private final BankService bankService;

    @Operation(
            summary = "Авторизация платежа",
            description = "Проверяет возможность проведения платежа через банк-эмитент"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Банк принял, либо отклонил платеж",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = GatewayAuthorizationResponse.class)
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
    @PostMapping("/authorize")
    public ResponseEntity<GatewayAuthorizationResponse> authorize(@RequestBody @Valid GatewayAuthorizationRequest request,
                                                                  @DecryptedCardData com.howwow.cppcarddecryptionstarter.carddecrypt.dto.DecryptedCardData decryptedCardData) {
        return ResponseEntity.ok(bankService.authorize(request, decryptedCardData));
    }
}