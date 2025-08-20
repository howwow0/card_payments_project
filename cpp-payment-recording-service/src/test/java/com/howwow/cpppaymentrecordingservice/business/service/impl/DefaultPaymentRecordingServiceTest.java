package com.howwow.cpppaymentrecordingservice.business.service.impl;

import static org.junit.jupiter.api.Assertions.*;


import com.howwow.cpppaymentrecordingservice.business.mapper.PaymentRecordingMapper;
import com.howwow.cpppaymentrecordingservice.business.persistence.entity.PaymentEntity;
import com.howwow.cpppaymentrecordingservice.business.persistence.repository.PaymentRepository;
import com.howwow.cpppaymentrecordingservice.rest.dto.response.PaymentResponse;
import com.howwow.cpppaymentrecordingservice.rest.exception.PaymentNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.*;

class DefaultPaymentRecordingServiceTest {

    private PaymentRepository paymentRepository;
    private PaymentRecordingMapper paymentRecordingMapper;
    private DefaultPaymentRecordingService service;

    @BeforeEach
    void setUp() {
        paymentRepository = mock(PaymentRepository.class);
        paymentRecordingMapper = mock(PaymentRecordingMapper.class);
        service = new DefaultPaymentRecordingService(paymentRepository, paymentRecordingMapper);
    }

    @Test
    void createPayment_savesPaymentAndReturnsSavedEntity() {
        PaymentEntity payment = new PaymentEntity(
                "reason",
                true,
                UUID.randomUUID(),
                UUID.randomUUID(),
                "test@example.com",
                UUID.randomUUID(),
                Currency.getInstance("USD"),
                BigDecimal.valueOf(100)
        );

        when(paymentRepository.save(any(PaymentEntity.class))).thenReturn(payment);

        PaymentEntity saved = service.createPayment(payment);

        assertNotNull(saved);
        assertEquals(payment.getEmail(), saved.getEmail());
        assertEquals(payment.getAmount(), saved.getAmount());

        verify(paymentRepository, times(1)).save(payment);
    }

    @Test
    void getPayment_existingPayment_returnsResponse() {
        UUID id = UUID.randomUUID();
        PaymentEntity entity = new PaymentEntity(
                "reason",
                true,
                UUID.randomUUID(),
                UUID.randomUUID(),
                "test@example.com",
                UUID.randomUUID(),
                Currency.getInstance("USD"),
                BigDecimal.valueOf(200)
        );

        PaymentResponse response = new PaymentResponse(
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

        when(paymentRepository.findById(id)).thenReturn(Optional.of(entity));
        when(paymentRecordingMapper.asPaymentResponse(entity)).thenReturn(response);

        PaymentResponse result = service.getPayment(id);

        assertNotNull(result);
        assertEquals(entity.getEmail(), result.email());
        verify(paymentRepository, times(1)).findById(id);
        verify(paymentRecordingMapper, times(1)).asPaymentResponse(entity);
    }

    @Test
    void getPayment_nonExistingPayment_throwsException() {
        UUID id = UUID.randomUUID();
        when(paymentRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(PaymentNotFoundException.class, () -> service.getPayment(id));

        verify(paymentRepository, times(1)).findById(id);
        verifyNoInteractions(paymentRecordingMapper);
    }
}
