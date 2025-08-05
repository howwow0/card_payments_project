package com.howwow.cppcardgateway.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.howwow.cppcardgateway.dto.GatewayPaymentAuthorizationRequest;
import com.howwow.cppcardgateway.validation.ValidationRule;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpRequestDecorator;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.nio.charset.StandardCharsets;

import static com.howwow.cppcardgateway.filter.utils.ResponseUtils.onError;

@Component
public class CardValidationGatewayFilterFactory extends AbstractGatewayFilterFactory<CardValidationGatewayFilterFactory.Config> {

    @Setter(onMethod_ = @Autowired)
    private ValidationRule<String> cvvValidationRule;

    @Setter(onMethod_ = @Autowired)
    private ValidationRule<String> expiryDateValidationRule;

    @Setter(onMethod_ = @Autowired)
    private ValidationRule<String> luhnValidationRule;

    @Setter(onMethod_ = @Autowired)
    private ObjectMapper objectMapper;

    public CardValidationGatewayFilterFactory() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> DataBufferUtils.join(exchange.getRequest().getBody())
                .publishOn(Schedulers.boundedElastic())
                .flatMap(dataBuffer -> {
                    byte[] bytes = new byte[dataBuffer.readableByteCount()];
                    dataBuffer.read(bytes);
                    DataBufferUtils.release(dataBuffer);

                    String bodyString = new String(bytes, StandardCharsets.UTF_8);

                    try {
                        GatewayPaymentAuthorizationRequest request = objectMapper.readValue(bodyString, GatewayPaymentAuthorizationRequest.class);

                        if (!expiryDateValidationRule.isValid(request.expiryDate())) {
                            return onError(exchange, "Недействительная или просроченная дата на карте (требуется формат ММ/ГГ)");
                        }

                        if (!luhnValidationRule.isValid(request.cardNumber())) {
                            return onError(exchange, "Неверный номер карты (не удалось проверить номер карты)");
                        }

                        if (!cvvValidationRule.isValid(request.cvv())) {
                            return onError(exchange, "Неверный формат CVV кода (ожидается 3 или 4 символа)");
                        }


                        ServerHttpRequest mutatedRequest = new ServerHttpRequestDecorator(exchange.getRequest()) {
                            @Override
                            @NonNull
                            public Flux<DataBuffer> getBody() {
                                return Flux.defer(() -> {
                                    DataBuffer buffer = exchange.getResponse().bufferFactory().wrap(bytes);
                                    return Flux.just(buffer);
                                });
                            }
                        };

                        ServerWebExchange mutatedExchange = exchange.mutate()
                                .request(mutatedRequest)
                                .build();

                        return chain.filter(mutatedExchange);

                    } catch (Exception e) {
                        return onError(exchange, "Неверное тело запроса: " + e.getMessage());
                    }

                });
    }


    public static class Config {
    }

}
