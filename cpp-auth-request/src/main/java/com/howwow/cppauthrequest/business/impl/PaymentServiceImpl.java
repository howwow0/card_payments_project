package com.howwow.cppauthrequest.business.impl;

import com.howwow.cppauthrequest.business.PaymentService;
import com.howwow.cppauthrequest.rest.dto.request.PaymentAuthorizationRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static com.howwow.cppauthrequest.business.utils.MaskUtils.maskCardNumber;

@Service
@Slf4j
public class PaymentServiceImpl implements PaymentService {

    @Override
    public UUID authorize(PaymentAuthorizationRequest paymentAuthorizationRequest) {
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
        return UUID.randomUUID();
    }

}
