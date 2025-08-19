package com.howwow.cppcarddecryptionstarter.security;

import com.howwow.cppkeysstarter.keys.PrivateKeyService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http,
            CardAuthenticationFilter cardAuthenticationFilter
    ) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth.anyRequest().authenticated())
                .addFilterBefore(cardAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public CardAuthenticationFilter cardAuthenticationFilter(
            CardAuthConverter cardAuthConverter,
            CardAuthenticationProvider cardAuthenticationProvider
    ) {
        return new CardAuthenticationFilter(cardAuthConverter, cardAuthenticationProvider);
    }

    @Bean
    public CardAuthConverter cardAuthConverter(PrivateKeyService privateKeyService) {
        return new CardAuthConverter(privateKeyService);
    }

    @Bean
    public CardAuthenticationProvider cardAuthenticationProvider() {
        return new CardAuthenticationProvider();
    }
}
