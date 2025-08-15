package com.howwow.cppauthrequest.business;

import com.howwow.cppauthrequest.bankgateway.dto.request.PaymentAuthorizationGatewayRequest;
import com.howwow.cppauthrequest.bankgateway.saga.BankGatewayPaymentStep;
import com.howwow.cppauthrequest.business.saga.service.SagaService;
import com.howwow.cppauthrequest.logging.saga.LoggingPaymentStep;
import com.howwow.cppauthrequest.persistence.enums.PaymentStatus;
import com.howwow.cppauthrequest.rest.dto.request.PaymentAuthorizationRequest;
import com.howwow.cppauthrequest.rest.dto.response.PaymentAuthorizationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentSagaService {

    private final SagaService sagaService;
    private final TransactionIdGenerationStep transactionIdGenerationStep;
    private final BankGatewayPaymentStep bankGatewayPaymentStep;
    private final LoggingPaymentStep loggingPaymentStep;

    public PaymentAuthorizationResponse execute(PaymentAuthorizationRequest request) {
        bankGatewayPaymentStep.setRequest(new PaymentAuthorizationGatewayRequest(
                request.amount(),
                request.currency()
        ));

        sagaService.executeSaga(List.of(
                transactionIdGenerationStep,
                loggingPaymentStep,
                bankGatewayPaymentStep
        ));

        var bankGatewayResult = sagaService.getResult(bankGatewayPaymentStep.getClass());
        var transactionId = sagaService.getResult(transactionIdGenerationStep.getClass());
        return new PaymentAuthorizationResponse(UUID.fromString(transactionId), bankGatewayResult.bankTransactionId(), PaymentStatus.APPROVED, bankGatewayResult.reason());
    }
}
