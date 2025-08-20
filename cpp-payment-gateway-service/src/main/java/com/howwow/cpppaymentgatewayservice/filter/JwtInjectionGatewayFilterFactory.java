package com.howwow.cpppaymentgatewayservice.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.howwow.cpppaymentgatewayservice.client.SecurityClient;
import com.howwow.cpppaymentgatewayservice.client.dto.request.CardAuthRequest;
import com.howwow.cpppaymentgatewayservice.dto.request.GatewayPaymentAuthorizationRequest;
import com.howwow.cpppaymentgatewayservice.dto.request.PaymentAuthorizationRequest;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpRequestDecorator;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.nio.charset.StandardCharsets;

import static com.howwow.cpppaymentgatewayservice.filter.utils.ResponseUtils.onError;

@Component
@Slf4j
public class JwtInjectionGatewayFilterFactory extends AbstractGatewayFilterFactory<JwtInjectionGatewayFilterFactory.Config> {

    @Setter(onMethod_ = @Autowired)
    private SecurityClient securityClient;

    @Setter(onMethod_ = @Autowired)
    private ObjectMapper objectMapper;

    public JwtInjectionGatewayFilterFactory() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> DataBufferUtils.join(exchange.getRequest().getBody())
                .publishOn(Schedulers.boundedElastic())
                .flatMap(dataBuffer -> {
                    try {
                        byte[] bytes = new byte[dataBuffer.readableByteCount()];
                        dataBuffer.read(bytes);
                        DataBufferUtils.release(dataBuffer);

                        String bodyString = new String(bytes, StandardCharsets.UTF_8);

                        GatewayPaymentAuthorizationRequest request;

                        request = objectMapper.readValue(bodyString, GatewayPaymentAuthorizationRequest.class);

                        return Mono.fromCallable(() ->
                                        securityClient.generateToken(new CardAuthRequest(request.cardNumber(), request.expiryDate(), request.cvv()))
                                )
                                .subscribeOn(Schedulers.boundedElastic())
                                .flatMap(response -> {
                                    try {
                                        PaymentAuthorizationRequest newRequest = new PaymentAuthorizationRequest(
                                                request.amount(),
                                                request.currency(),
                                                request.merchantId(),
                                                request.email()
                                        );

                                        byte[] newBodyBytes;

                                        newBodyBytes = objectMapper.writeValueAsBytes(newRequest);
                                        ServerHttpRequest mutatedRequest = new ServerHttpRequestDecorator(exchange.getRequest()) {
                                            @Override
                                            @NonNull
                                            public Flux<DataBuffer> getBody() {
                                                DataBuffer buffer = exchange.getResponse().bufferFactory().wrap(newBodyBytes);
                                                return Flux.just(buffer);
                                            }

                                            @Override
                                            @NonNull
                                            public HttpHeaders getHeaders() {
                                                HttpHeaders headers = new HttpHeaders();
                                                headers.putAll(exchange.getRequest().getHeaders());
                                                headers.setContentLength(newBodyBytes.length);
                                                headers.set(HttpHeaders.AUTHORIZATION, "Bearer " + response.token());
                                                return headers;
                                            }
                                        };

                                        ServerWebExchange mutatedExchange = exchange.mutate()
                                                .request(mutatedRequest)
                                                .build();

                                        return chain.filter(mutatedExchange);
                                    } catch (Exception e) {
                                        return onError(exchange, "Ошибка сериализации нового тела запроса: " + e.getMessage());
                                    }
                                });
                    } catch (Exception e) {
                        return onError(exchange, "Неверное тело запроса: " + e.getMessage());
                    }
                });
    }

    public static class Config {
    }
}
