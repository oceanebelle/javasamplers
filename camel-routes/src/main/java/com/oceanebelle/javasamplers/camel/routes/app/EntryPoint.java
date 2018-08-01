package com.oceanebelle.javasamplers.camel.routes.app;

import com.oceanebelle.javasamplers.camel.routes.config.AppConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackageClasses = AppConfig.class)
public class EntryPoint {
    private static final Logger LOG = LoggerFactory.getLogger(EntryPoint.class);

    public static void main(String[] args) {
        SpringApplication.run(EntryPoint.class, args);
    }
}
