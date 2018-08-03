package com.oceanebelle.javasamplers.camel.routes.config;

import org.apache.camel.spring.boot.CamelConfigurationProperties;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class TestConfig {
    @Bean
    CamelConfigurationProperties properties() {
        return new CamelConfigurationProperties();
    }
}
