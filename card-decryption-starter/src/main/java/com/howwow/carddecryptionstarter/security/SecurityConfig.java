package com.howwow.carddecryptionstarter.security;

import com.howwow.carddecryptionstarter.config.KeysLoader;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final KeysLoader keysLoader;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorize -> authorize
                        .anyRequest().authenticated()
                )
                .addFilterBefore(
                        new CardAuthenticationFilter(cardAuthConverter(), cardAuthProvider()),
                        UsernamePasswordAuthenticationFilter.class
                );

        return http.build();
    }

    @Bean
    public CardAuthConverter cardAuthConverter() {
        return new CardAuthConverter(keysLoader);
    }

    @Bean
    public CardAuthenticationProvider cardAuthProvider() {
        return new CardAuthenticationProvider();
    }
}
