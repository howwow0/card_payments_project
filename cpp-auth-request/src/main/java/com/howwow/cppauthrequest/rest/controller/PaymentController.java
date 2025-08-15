package com.howwow.cppauthrequest.rest.controller;

import com.howwow.cppauthrequest.business.PaymentSagaService;
import com.howwow.cppauthrequest.rest.dto.request.PaymentAuthorizationRequest;
import com.howwow.cppauthrequest.rest.dto.response.PaymentAuthorizationResponse;
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
public class PaymentController {

    private final PaymentSagaService paymentSagaService;

    @PostMapping("/authorize")
    public ResponseEntity<PaymentAuthorizationResponse> authorize(@RequestBody @Valid PaymentAuthorizationRequest paymentAuthorizationRequest) {
        return ResponseEntity.ok(paymentSagaService.execute(paymentAuthorizationRequest));
    }
}
