package com.oceanebelle.javasamplers.camelcamelcamel.app;

import com.oceanebelle.javasamplers.camelcamelcamel.config.AppConfiguration;
import com.oceanebelle.javasamplers.camelcamelcamel.config.routes.SimpleDirectPipelineRoute;
import org.apache.camel.CamelContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import java.util.concurrent.Future;

@SpringBootApplication
@Import(AppConfiguration.class)
public class App implements ApplicationRunner {
    private static final Logger LOG = LoggerFactory.getLogger(App.class);

    @Autowired
    CamelContext context;

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        LOG.info("START application.");

        context.start();

        Future<Boolean> result = context.createFluentProducerTemplate()
                .to(SimpleDirectPipelineRoute.DIRECT_COMPONENT_ROUTE)
                .asyncRequest(Boolean.class);

        result.get();


        LOG.info("DONE. {}", result.get());

    }
}
