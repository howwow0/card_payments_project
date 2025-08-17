package com.howwow.cppbankgatewayservice.rest.controller;

import com.howwow.cppbankgatewayservice.business.BankService;
import com.howwow.cppbankgatewayservice.rest.dto.request.GatewayAuthorizationRequest;
import com.howwow.cppbankgatewayservice.rest.dto.response.GatewayAuthorizationResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
            description = "Проверяет возможность проведения платежа через банк-эмитент",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Успешная авторизация",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = GatewayAuthorizationResponse.class)
                            ))
            }
    )
    @PostMapping("/authorize")
    public ResponseEntity<GatewayAuthorizationResponse> authorize(@RequestBody @Valid GatewayAuthorizationRequest request) {
        return ResponseEntity.ok(bankService.authorize(request));
    }
}