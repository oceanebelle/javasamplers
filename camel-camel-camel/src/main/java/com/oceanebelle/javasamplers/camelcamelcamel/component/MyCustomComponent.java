package com.oceanebelle.javasamplers.camelcamelcamel.component;

import org.apache.camel.Endpoint;
import org.apache.camel.impl.UriEndpointComponent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class MyCustomComponent extends UriEndpointComponent {

    private static final Logger LOG = LoggerFactory.getLogger(MyCustomComponent.class);

    private static final Map<String, MyCustomEndpoint> ENDPOINT_MAP = new HashMap<>();

    public MyCustomComponent() {
        super(MyCustomEndpoint.class);
    }

    @Override
    public Endpoint createEndpoint(String uri) throws Exception {
        return createEndpointInstance(uri);
    }

    private MyCustomEndpoint createEndpointInstance(String uri) {
        if (!ENDPOINT_MAP.containsKey(uri)) {
            LOG.info("Creating an endpoint {}", uri);
            MyCustomEndpoint endpoint = new MyCustomEndpoint(uri);
            ENDPOINT_MAP.put(uri, endpoint);
        }
        return ENDPOINT_MAP.get(uri);
    }

    @Override
    protected Endpoint createEndpoint(String uri, String remaining, Map<String, Object> parameters) throws Exception {
        LOG.info("Creating an endpoint {} with remaining {}", uri, remaining);
        MyCustomEndpoint endpoint = createEndpointInstance(uri);

        this.setProperties(endpoint, parameters);

        return endpoint;
    }
}
