package com.oceanebelle.javasamplers.camelcamelcamel.config;

import com.oceanebelle.javasamplers.camelcamelcamel.config.routes.SimpleDirectPipelineRoute;
import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration {
    public final static String CAMEL_CONTEXT = "myAppCamelContext";

    @Bean
    public SimpleDirectPipelineRoute routeA() throws Exception {
        return new SimpleDirectPipelineRoute(camelContext());
    }

    @Bean(CAMEL_CONTEXT)
    public CamelContext camelContext() throws Exception {
        DefaultCamelContext context = new DefaultCamelContext();
        //context.addComponent("mycustom", new MyCustomComponent());
        context.setAutoStartup(true);
        return context;
    }
}
