package com.howwow.cppsecurityservice.business;

import com.howwow.cppsecurityservice.rest.dto.request.CardAuthRequest;
import com.howwow.cppsecurityservice.rest.dto.response.TokenResponse;

public interface JwtService {
    TokenResponse generateToken(CardAuthRequest cardAuthRequest);
}
