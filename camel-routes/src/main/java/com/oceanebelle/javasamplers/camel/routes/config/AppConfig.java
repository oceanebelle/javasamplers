package com.oceanebelle.javasamplers.camel.routes.config;

import com.oceanebelle.javasamplers.camel.routes.controller.AppController;
import com.oceanebelle.javasamplers.camel.routes.router.AppRoutes;
import org.apache.camel.CamelContext;
import org.apache.camel.spring.CamelBeanPostProcessor;
import org.apache.camel.spring.SpringCamelContext;
import org.apache.camel.spring.boot.CamelConfigurationProperties;
import org.apache.camel.spring.boot.CamelContextConfiguration;
import org.apache.camel.spring.boot.RoutesCollector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;

import java.util.ArrayList;
import java.util.Collection;

@Configuration
@ComponentScans({
        @ComponentScan(basePackageClasses = AppRoutes.class),
        @ComponentScan(basePackageClasses = AppController.class)
})
@PropertySource("classpath:defaults.properties")
public class AppConfig {

    @Autowired
    ApplicationContext context;

    @Bean
    @ConditionalOnMissingBean(CamelContext.class)
    public CamelContext camelContext() {
        return new SpringCamelContext(context);
    }

    @Bean
    //@ConditionalOnMissingBean(RoutesCollector.class)
    RoutesCollector routesCollector(ApplicationContext applicationContext, CamelConfigurationProperties camelConfigurationProperties) {
        Collection<CamelContextConfiguration> configurations = applicationContext.getBeansOfType(CamelContextConfiguration.class).values();
        return new RoutesCollector(applicationContext, new ArrayList(configurations), camelConfigurationProperties);
    }

    /**
     * Camel post processor - required to support Camel annotations.
     */
    @Bean
    CamelBeanPostProcessor camelBeanPostProcessor(ApplicationContext applicationContext) {
        CamelBeanPostProcessor processor = new CamelBeanPostProcessor();
        processor.setApplicationContext(applicationContext);
        return processor;
    }
}
