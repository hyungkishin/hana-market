package com.hanamarket.config.jdbc.datasource;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@NoArgsConstructor
@ConfigurationProperties(prefix = "spring.datasource.read")
public class DataSourceReadProperties {

    private String driverClassName;

    private String jdbcUrl;

    private String username;

    private String password;

}
