package com.howwow.cppauthrequest.client.dto.response;

import java.util.UUID;

public record PaymentAuthorizationGatewayResponse(
        UUID bankTransactionId,
        boolean isApproved,
        String reason
) {
}
