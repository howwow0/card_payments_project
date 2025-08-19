package com.howwow.cppbankgatewayservice.business;

import com.howwow.cppbankgatewayservice.rest.dto.request.GatewayAuthorizationRequest;
import com.howwow.cppbankgatewayservice.rest.dto.response.GatewayAuthorizationResponse;
import com.howwow.cppcarddecryptionstarter.carddecrypt.dto.DecryptedCardData;

public interface BankService {
    GatewayAuthorizationResponse authorize(GatewayAuthorizationRequest request, DecryptedCardData decryptedCardData);
}
