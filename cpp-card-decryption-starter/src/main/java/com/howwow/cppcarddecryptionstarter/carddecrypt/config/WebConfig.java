package com.howwow.cppcarddecryptionstarter.carddecrypt.config;

import com.howwow.cppcarddecryptionstarter.carddecrypt.DecryptedCardDataArgumentResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Component
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

    private final DecryptedCardDataArgumentResolver decryptedCardDataArgumentResolver;

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(decryptedCardDataArgumentResolver);
    }
}
