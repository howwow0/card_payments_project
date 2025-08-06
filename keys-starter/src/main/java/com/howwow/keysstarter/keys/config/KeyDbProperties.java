package com.howwow.keysstarter.keys.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "keydb.datasource")
@Getter
@Setter
public class KeyDbProperties {
    private String url;
    private String username;
    private String password;
}
