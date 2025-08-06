package com.howwow.cppcardgateway.client;

import com.howwow.cppcardgateway.client.dto.request.CardAuthRequest;
import com.howwow.cppcardgateway.client.dto.response.TokenResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface SecurityClient {
    @PostMapping(
            path = "/api/v1/security/generate",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    TokenResponse generateToken(@RequestBody CardAuthRequest body);
}
