package com.howwow.carddecryptionstarter.carddecrypt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.howwow.carddecryptionstarter.carddecrypt.annotation.DecryptedCardData;
import com.howwow.carddecryptionstarter.carddecrypt.dto.CardDataDto;
import com.howwow.carddecryptionstarter.carddecrypt.service.CardDataDecryptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.lang.NonNull;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * Добавляет возможность в контроллерах указать @DecryptedCardData и получить данные карты в качестве DTO CardDataDto.
 * Данные вытаскиваются из SpringSecurity JWT и расшифровываются сервисом CardDataDecryptionService.
 */
@Component
@RequiredArgsConstructor
public class DecryptedCardDataArgumentResolver implements HandlerMethodArgumentResolver {

    private final CardDataDecryptionService decryptionService;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(DecryptedCardData.class)
                && parameter.getParameterType().equals(CardDataDto.class);
    }

    @Override
    public Object resolveArgument(@NonNull MethodParameter parameter,
                                  ModelAndViewContainer mavContainer,
                                  @NonNull NativeWebRequest webRequest,
                                  WebDataBinderFactory binderFactory) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        DecodedJWT decodedJWT = JWT.decode(auth.getCredentials().toString());
        System.out.println("cardNumber claim: " + decodedJWT.getClaim("cardNumber").asString());
        System.out.println("expiryDate claim: " + decodedJWT.getClaim("expiryDate").asString());
        System.out.println("cvv claim: " + decodedJWT.getClaim("cvv").asString());
        return new CardDataDto(
                decryptionService.decrypt(decodedJWT.getClaim("cardNumber").asString()),
                decryptionService.decrypt(decodedJWT.getClaim("expiryDate").asString()),
                decryptionService.decrypt(decodedJWT.getClaim("cvv").asString())
        );
    }

}
