package com.howwow.cpppaymentorchestratorservice.persistence.enums;

public enum PaymentStatus {
    APPROVED,
    REJECTED;

    public static PaymentStatus isApproved(boolean isApproved) {
        if (isApproved) return APPROVED;
        else return REJECTED;
    }
}
