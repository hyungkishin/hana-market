package com.hanamarket.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@AllArgsConstructor
@ConfigurationProperties(prefix = "spring.datasource")
public class DBProperties {
    private String driverClassName;

    private String url;

    private String username;

    private String password;
}

