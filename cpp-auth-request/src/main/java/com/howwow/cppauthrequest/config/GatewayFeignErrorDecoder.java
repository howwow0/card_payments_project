package com.howwow.cppauthrequest.config;

import com.howwow.cppauthrequest.client.exception.GatewayUnavailableException;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.stereotype.Component;

@Component
public class GatewayFeignErrorDecoder implements ErrorDecoder {

    private final ErrorDecoder defaultDecoder = new Default();

    @Override
    public Exception decode(String methodKey, Response response) {
        int status = response.status();

        return switch (status) {
            case 500, 502, 503, 504 -> new GatewayUnavailableException();
            default -> defaultDecoder.decode(methodKey, response);
        };
    }
}
