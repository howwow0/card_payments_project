package com.howwow.cpppaymentrecordingservice.business.mapper;

import com.howwow.cpppaymentrecordingservice.business.persistence.entity.PaymentEntity;
import com.howwow.cpppaymentrecordingservice.rest.dto.response.PaymentResponse;
import org.springframework.stereotype.Component;

@Component
public class PaymentRecordingMapper {

    public PaymentResponse asPaymentResponse(PaymentEntity entity) {
        return new PaymentResponse(
                entity.getId(),
                entity.getTransactionId(),
                entity.getBankTransactionId(),
                entity.getMerchantId(),
                entity.getEmail(),
                entity.getAmount(),
                entity.getCurrency().getCurrencyCode(),
                entity.getIsApproved(),
                entity.getReason(),
                entity.getCreatedAt()
        );
    }
}
