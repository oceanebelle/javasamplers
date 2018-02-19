package com.oceanebelle.javasamplers.camelcamelcamel.component;

import org.apache.camel.Exchange;
import org.apache.camel.impl.DefaultProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyCustomProducer extends DefaultProducer {
    private static final Logger LOG = LoggerFactory.getLogger(MyCustomProducer.class);
    private final MyCustomEndpoint endpoint;

    public MyCustomProducer(MyCustomEndpoint endpoint) {
        super(endpoint);
        this.endpoint = endpoint;
    }

    @Override
    public void process(Exchange exchange) throws Exception {
        LOG.info("Processing producer ... Do something");

        if (exchange.hasOut()) {
            exchange.getOut().setBody("HELLO RESULT");
        } else {
            exchange.getIn().setBody("HELLO ELSE RESULT");
        }

        endpoint.getQueue().offer(exchange);
    }


}
