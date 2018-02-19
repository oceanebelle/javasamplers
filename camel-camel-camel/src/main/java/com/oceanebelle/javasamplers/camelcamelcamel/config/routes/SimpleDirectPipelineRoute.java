package com.oceanebelle.javasamplers.camelcamelcamel.config.routes;


import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.ExchangePattern;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class SimpleDirectPipelineRoute extends RouteBuilder {
    private static final Logger LOG = LoggerFactory.getLogger(SimpleDirectPipelineRoute.class);

    public final static String DIRECT_COMPONENT_ROUTE = "direct:ccc";
    public final static String MYCUSTOM_COMPONENT_ROUTE = "mycustom:abc";

    @Autowired
    public SimpleDirectPipelineRoute(CamelContext context) {
        super(context);
    }

    @Override
    public void configure() throws Exception {
        from(MYCUSTOM_COMPONENT_ROUTE)
                .id("CONSUME-mycustom")
                .to("log:consumer-body-start")
                .setBody()
                .simple(Boolean.TRUE.toString())
                .to("log:consumer-result")
                .convertBodyTo(Boolean.class)
                .end();

        from(DIRECT_COMPONENT_ROUTE)
                .id("PRODUCE-mycustom")
                .setExchangePattern(ExchangePattern.InOut)
                .process(new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {
                        LOG.info("Process osomething");
                        exchange.getOut().setBody("INTERMEDIATE");
                    }
                })
                .to("log:producer-result")
                .to(MYCUSTOM_COMPONENT_ROUTE)
                .end();



    }
}
