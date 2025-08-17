package com.howwow.cppbankgatewayservice.business;

import com.howwow.cppbankgatewayservice.rest.dto.request.GatewayAuthorizationRequest;
import com.howwow.cppbankgatewayservice.rest.dto.response.GatewayAuthorizationResponse;

public interface BankService {
    GatewayAuthorizationResponse authorize(GatewayAuthorizationRequest request);
}
