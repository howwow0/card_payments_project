package com.howwow.cppbankgateway.business;

import com.howwow.cppbankgateway.rest.dto.request.GatewayAuthorizationRequest;
import com.howwow.cppbankgateway.rest.dto.response.GatewayAuthorizationResponse;

public interface BankService {
    GatewayAuthorizationResponse authorize(GatewayAuthorizationRequest request);
}
