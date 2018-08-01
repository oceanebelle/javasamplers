package com.oceanebelle.javasamplers.camel.routes.config;

import com.oceanebelle.javasamplers.camel.routes.controller.AppController;
import com.oceanebelle.javasamplers.camel.routes.router.AppRoutes;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScans(        {
        @ComponentScan(basePackageClasses = AppRoutes.class),
        @ComponentScan(basePackageClasses = AppController.class)
        })
@PropertySource("defaults.properties")
public class AppConfig {

}
