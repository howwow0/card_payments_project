package com.howwow.cppcarddecryptionstarter.carddecrypt;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.howwow.cppcarddecryptionstarter.carddecrypt.dto.DecryptedCardData;
import com.howwow.cppcarddecryptionstarter.carddecrypt.service.CardDataDecryptionService;
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
@RequiredArgsConstructor
public class DecryptedCardDataArgumentResolver implements HandlerMethodArgumentResolver {

    private final CardDataDecryptionService decryptionService;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(com.howwow.cppcarddecryptionstarter.carddecrypt.annotation.DecryptedCardData.class)
                && parameter.getParameterType().equals(DecryptedCardData.class);
    }

    @Override
    public Object resolveArgument(@NonNull MethodParameter parameter,
                                  ModelAndViewContainer mavContainer,
                                  @NonNull NativeWebRequest webRequest,
                                  WebDataBinderFactory binderFactory) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        DecodedJWT decodedJWT = (DecodedJWT) auth.getCredentials();
        return new DecryptedCardData(
                decryptionService.decrypt(decodedJWT.getClaim("cardNumber").asString()),
                decryptionService.decrypt(decodedJWT.getClaim("expiryDate").asString()),
                decryptionService.decrypt(decodedJWT.getClaim("cvv").asString())
        );
    }

}
