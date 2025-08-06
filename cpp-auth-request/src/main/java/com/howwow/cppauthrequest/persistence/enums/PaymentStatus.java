package com.howwow.cppauthrequest.persistence.enums;

public enum PaymentStatus {
    APPROVED,
    REJECTED;

    public static PaymentStatus isApproved(boolean isApproved) {
        if (isApproved) return APPROVED;
        else return REJECTED;
    }
}
