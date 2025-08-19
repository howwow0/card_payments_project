package com.howwow.cpppaymentrecordingservice.business.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Currency;
import java.util.UUID;

@Entity
@Table(name = "payments", schema = "cpp_payment_schema")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PaymentEntity {

    @Id
    @UuidGenerator
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "amount", nullable = false)
    private BigDecimal amount;

    @Column(name = "currency", nullable = false)
    private Currency currency;

    @Column(name = "merchant_id", nullable = false)
    private UUID merchantId;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "transaction_id", nullable = false, unique = true)
    private UUID transactionId;

    @Column(name = "bank_transaction_id", nullable = false, unique = true)
    private UUID bankTransactionId;

    @Column(name = "is_approved", nullable = false)
    private Boolean isApproved;

    @Column(name = "reason")
    private String reason;

    @Column(name = "created_at", nullable = false, updatable = false)
    @CreationTimestamp
    private Instant createdAt;

    public PaymentEntity(String reason, Boolean isApproved, UUID bankTransactionId, UUID transactionId, String email, UUID merchantId, Currency currency, BigDecimal amount) {
        this.reason = reason;
        this.isApproved = isApproved;
        this.bankTransactionId = bankTransactionId;
        this.transactionId = transactionId;
        this.email = email;
        this.merchantId = merchantId;
        this.currency = currency;
        this.amount = amount;
    }
}
