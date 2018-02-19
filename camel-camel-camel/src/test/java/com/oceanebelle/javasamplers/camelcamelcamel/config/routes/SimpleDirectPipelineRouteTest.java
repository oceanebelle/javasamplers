package com.oceanebelle.javasamplers.camelcamelcamel.config.routes;

import org.apache.camel.RoutesBuilder;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class SimpleDirectPipelineRouteTest extends CamelTestSupport {

    RouteBuilder route;

    /**
     * This is requied to test a route
     * @return
     * @throws Exception
     */
    @Override
    protected RoutesBuilder createRouteBuilder() throws Exception {
        SimpleDirectPipelineRoute simple = new SimpleDirectPipelineRoute(context());
        return simple;
    }

    @Before
    public void setup() {
        route = new SimpleDirectPipelineRoute(context());
    }

    @Test
    public void testRoute() throws ExecutionException, InterruptedException {
        Future<Boolean> result = fluentTemplate()
                .to(SimpleDirectPipelineRoute.DIRECT_COMPONENT_ROUTE)
                .asyncRequest(Boolean.class);

        assertNotNull(result.get());
    }
}
