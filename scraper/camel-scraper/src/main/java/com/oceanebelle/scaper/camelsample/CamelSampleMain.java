package com.oceanebelle.scaper.camelsample;

import org.apache.camel.CamelContext;
import org.apache.camel.FluentProducerTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CamelSampleMain implements CommandLineRunner {
    private static final Logger LOG = LoggerFactory.getLogger(CamelSampleMain.class);
    private final CamelContext context;

    @Autowired
    public CamelSampleMain(CamelContext context) {
        this.context = context;
    }

    public static void main(String ... args) {
        SpringApplication.run(CamelSampleMain.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        LOG.info("Started application");

        context.startAllRoutes();

        FluentProducerTemplate producer = context.createFluentProducerTemplate();

        producer.withHeader(CamelRoute.URL, "https://google.com")
                .to(CamelRoute.FETCH_URL)
                .send();

    }
}
