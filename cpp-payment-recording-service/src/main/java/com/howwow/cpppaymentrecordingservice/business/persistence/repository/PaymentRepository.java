package com.howwow.cpppaymentrecordingservice.business.persistence.repository;

import com.howwow.cpppaymentrecordingservice.business.persistence.entity.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PaymentRepository extends JpaRepository<PaymentEntity, UUID> {
}
