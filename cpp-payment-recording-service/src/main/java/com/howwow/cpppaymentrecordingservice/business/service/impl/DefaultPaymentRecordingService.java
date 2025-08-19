package com.howwow.cpppaymentrecordingservice.business.service.impl;

import com.howwow.cpppaymentrecordingservice.business.mapper.PaymentRecordingMapper;
import com.howwow.cpppaymentrecordingservice.business.persistence.entity.PaymentEntity;
import com.howwow.cpppaymentrecordingservice.business.persistence.repository.PaymentRepository;
import com.howwow.cpppaymentrecordingservice.business.service.PaymentRecordingService;
import com.howwow.cpppaymentrecordingservice.rest.dto.response.PaymentResponse;
import com.howwow.cpppaymentrecordingservice.rest.exception.PaymentNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class DefaultPaymentRecordingService implements PaymentRecordingService {

    private final PaymentRepository paymentRepository;
    private final PaymentRecordingMapper paymentRecordingMapper;

    @Override
    @Transactional
    public PaymentEntity createPayment(PaymentEntity paymentEntity) {
        PaymentEntity saved = paymentRepository.save(paymentEntity);
        log.info(
                "Создан платеж: transactionId={}, bankTransactionId={}, merchantId={}, email={}, amount={} {}, approved={}, reason={}",
                saved.getTransactionId(),
                saved.getBankTransactionId(),
                saved.getMerchantId(),
                saved.getEmail(),
                saved.getAmount(),
                saved.getCurrency().getCurrencyCode(),
                saved.getIsApproved(),
                saved.getReason()
        );
        return saved;
    }

    @Override
    public PaymentResponse getPayment(UUID paymentId) {
        PaymentEntity entity = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new PaymentNotFoundException(paymentId));
        return paymentRecordingMapper.asPaymentResponse(entity);
    }
}
