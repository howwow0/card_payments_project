package com.howwow.cppauthrequest.bankgateway.service.impl;

import com.howwow.cppauthrequest.bankgateway.client.BankGatewayClient;
import com.howwow.cppauthrequest.bankgateway.dto.request.PaymentAuthorizationGatewayRequest;
import com.howwow.cppauthrequest.bankgateway.dto.response.PaymentAuthorizationGatewayResponse;
import com.howwow.cppauthrequest.bankgateway.service.BankGatewayService;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class BankGatewayServiceImpl implements BankGatewayService {
    private final BankGatewayClient bankGatewayClient;

    @Override
    public PaymentAuthorizationGatewayResponse authorize(PaymentAuthorizationGatewayRequest request) {
        try {
            return bankGatewayClient.authorizePayment(request);
        } catch (FeignException fe) {
            int status = fe.status();

            if (status == 403) {
                return new PaymentAuthorizationGatewayResponse(null, false, "Нет доступа, попробуйте ещё раз");
            } else if (status >= 500 && status < 600) {
                return new PaymentAuthorizationGatewayResponse(null, false, "Сервис банка недоступен, попробуйте позже");
            } else {
                return new PaymentAuthorizationGatewayResponse(null, false, "Произошла ошибка, попробуйте позже");
            }

        } catch (Exception e) {
            log.error("Неизвестная ошибка: {}", e.getMessage(), e);
            return new PaymentAuthorizationGatewayResponse(null, false, "Произошла ошибка, попробуйте позже");
        }
    }
}
