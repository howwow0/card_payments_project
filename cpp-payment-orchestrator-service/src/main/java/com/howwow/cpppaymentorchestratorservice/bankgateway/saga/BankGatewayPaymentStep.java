package com.howwow.cpppaymentorchestratorservice.bankgateway.saga;

import com.howwow.cpppaymentorchestratorservice.bankgateway.dto.request.PaymentAuthorizationGatewayRequest;
import com.howwow.cpppaymentorchestratorservice.bankgateway.dto.response.PaymentAuthorizationGatewayResponse;
import com.howwow.cpppaymentorchestratorservice.bankgateway.service.BankGatewayService;
import com.howwow.cpppaymentorchestratorservice.business.saga.SagaContext;
import com.howwow.cpppaymentorchestratorservice.business.saga.SagaStep;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class BankGatewayPaymentStep implements SagaStep<PaymentAuthorizationGatewayResponse> {

    private final BankGatewayService bankGatewayService;
    private PaymentAuthorizationGatewayRequest request;

    public BankGatewayPaymentStep setRequest(PaymentAuthorizationGatewayRequest request) {
        this.request = request;
        return this;
    }

    @Override
    public String getName() {
        return "BankGatewayStep";
    }

    @Override
    public PaymentAuthorizationGatewayResponse execute(SagaContext context) {
        return bankGatewayService.authorize(request);
    }

    @Override
    public void compensate(SagaContext context) {
        log.warn("Компенсация шага BankGatewayPaymentStep не требуется.");
    }
}
