package com.howwow.cppauthrequest.business.impl;

import com.howwow.cppauthrequest.business.GatewayAdapterService;
import com.howwow.cppauthrequest.business.PaymentFacadeService;
import com.howwow.cppauthrequest.business.mapper.PaymentFacadeMapper;
import com.howwow.cppauthrequest.client.dto.response.PaymentAuthorizationGatewayResponse;
import com.howwow.cppauthrequest.rest.dto.request.PaymentAuthorizationRequest;
import com.howwow.cppauthrequest.rest.dto.response.PaymentAuthorizationResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static com.howwow.cppauthrequest.business.utils.MaskUtils.maskCardNumber;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentFacadeServiceImpl implements PaymentFacadeService {

    private final GatewayAdapterService gatewayAdapterService;
    private final PaymentFacadeMapper paymentFacadeMapper;

    @Override
    public PaymentAuthorizationResponse authorizePayment(PaymentAuthorizationRequest paymentAuthorizationRequest) {
        log.info("""
                        Попытка авторизации платежа:
                            - Card: {}
                            - Amount: {} {}
                            - Merchant: {}
                        """,
                maskCardNumber(paymentAuthorizationRequest.cardNumber()),
                paymentAuthorizationRequest.amount(),
                paymentAuthorizationRequest.currency().getCurrencyCode(),
                paymentAuthorizationRequest.merchantId()
        );
        UUID transactionId = UUID.randomUUID();
        PaymentAuthorizationGatewayResponse response = gatewayAdapterService.authorize(
                paymentFacadeMapper.toPaymentAuthorizationGatewayRequest(paymentAuthorizationRequest));
        PaymentAuthorizationResponse authorizationResponse = paymentFacadeMapper.toPaymentAuthorizationResponse(transactionId, response);
        log.info("""
                        Платеж обработан банком - эмитентом:
                            - Card: {}
                            - Amount: {} {}
                            - Merchant: {}
                            - TransactionId: {}
                            - BankTransactionId: {}
                            - Status: {}
                        """,
                maskCardNumber(paymentAuthorizationRequest.cardNumber()),
                paymentAuthorizationRequest.amount(),
                paymentAuthorizationRequest.currency().getCurrencyCode(),
                paymentAuthorizationRequest.merchantId(),
                authorizationResponse.transactionId(),
                authorizationResponse.bankTransactionId(),
                authorizationResponse.status()
        );
        return authorizationResponse;
    }

}
