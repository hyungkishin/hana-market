package com.example.hanamarket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class HanaMarketApplication {

    public static void main(String[] args) {
        SpringApplication.run(HanaMarketApplication.class, args);
    }

}
