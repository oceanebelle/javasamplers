package com.oceanebelle.javasamplers.camel.routes.config;

import com.oceanebelle.javasamplers.camel.routes.router.AppRoutes;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.AdviceWithRouteBuilder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.atomic.AtomicBoolean;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.awaitility.Awaitility.await;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {AppConfig.class, TestConfig.class})
@DirtiesContext
public class AppTest {
    public static final AtomicBoolean IS_COMPLETE = new AtomicBoolean(false);

    @Autowired
    CamelContext camelContext;

    @Before
    public void setup() throws Exception {
        IS_COMPLETE.set(false);

        // The routes have been set to autoStart=false in test so modification is allowed.
        // Modify the routes with Advicewith
        camelContext.getRouteDefinition(AppRoutes.DATA_LOAD_INPUT_ID).adviceWith(camelContext, new AdviceWithRouteBuilder() {
            @Override
            public void configure() throws Exception {
                // Load the file directly when the route starts (effectively mocks the http call)
                interceptFrom().pollEnrich("file:./src/main/resources?fileName=large.txt&noop=true");

                // Remove the actual call to http as it's not needed
                weaveByToUri("http:.*").remove();

                // Set the async flag to complete at the end of the route
                weaveAddLast().process().body(x -> IS_COMPLETE.set(true));
            }
        });

        camelContext.startAllRoutes();
    }

    @Test
    public void testReadXMLRoute() {
        // Starts the test and the route
        ProducerTemplate producer = camelContext.createProducerTemplate();
        producer.asyncRequestBody("direct:test", null);

        // Wait until the route completes
        await().atMost(5, SECONDS).untilTrue(IS_COMPLETE);
    }
}
