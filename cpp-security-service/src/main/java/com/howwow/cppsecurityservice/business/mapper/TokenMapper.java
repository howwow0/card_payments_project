package com.howwow.cppsecurityservice.business.mapper;

import com.howwow.cppsecurityservice.rest.dto.response.TokenResponse;
import org.springframework.stereotype.Component;

@Component
public class TokenMapper {

    public TokenResponse asTokenResponse(String token) {
        return new TokenResponse(token);
    }
}
