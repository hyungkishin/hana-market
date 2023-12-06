package com.hanamarket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
@ConfigurationPropertiesScan
@EntityScan
public class HanaMarketApplication {

    public static final String BASE_PACKAGE_MYAPP = "com.hanamarket";

    public static void main(String[] args) {
        SpringApplication.run(HanaMarketApplication.class, args);
    }

}
