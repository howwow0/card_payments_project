package com.howwow.carddecryptionstarter.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

@Slf4j
public class CardAuthenticationProvider implements AuthenticationProvider {

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if (authentication instanceof CardAuthToken token && token.isAuthenticated()) {
            log.info("Пользователь {} прошёл аутентификацию", token.getPrincipal());
        } else {
            log.warn("Анонимный или неаутентифицированный пользователь");
        }
        return authentication;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return CardAuthToken.class.isAssignableFrom(authentication);
    }
}
