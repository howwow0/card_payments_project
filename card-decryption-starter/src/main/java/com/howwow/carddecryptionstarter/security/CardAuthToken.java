package com.howwow.carddecryptionstarter.security;

import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.Getter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Collections;

@Getter
public class CardAuthToken extends AbstractAuthenticationToken {

    private final DecodedJWT jwt;

    public CardAuthToken(DecodedJWT jwt, Collection<? extends GrantedAuthority> authorities) {
        super(authorities != null ? authorities : Collections.emptyList());
        this.jwt = jwt;
        setAuthenticated(jwt != null);
    }

    @Override
    public Object getCredentials() {
        return jwt;
    }

    @Override
    public Object getPrincipal() {
        return jwt != null ? jwt.getId() : null;
    }
}
