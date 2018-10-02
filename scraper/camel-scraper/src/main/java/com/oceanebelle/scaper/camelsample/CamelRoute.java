package com.oceanebelle.scaper.camelsample;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class CamelRoute extends RouteBuilder {

    public static final String FETCH_URL = "direct:fetch";
    public static final String URL = "FETCH_URL";

    @Override
    public void configure() throws Exception {
        from(FETCH_URL)
                .setHeader(Exchange.HTTP_URI, simple("${in.header." + URL + "}"))
                .to("http4://scrapeTarget")
                .log("${messageHistory}")
                .log("${body}")
                .end();
    }
}
