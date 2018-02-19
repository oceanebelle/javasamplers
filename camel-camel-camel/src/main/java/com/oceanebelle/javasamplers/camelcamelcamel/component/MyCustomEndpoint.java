package com.oceanebelle.javasamplers.camelcamelcamel.component;

import org.apache.camel.Consumer;
import org.apache.camel.Exchange;
import org.apache.camel.PollingConsumer;
import org.apache.camel.Processor;
import org.apache.camel.Producer;
import org.apache.camel.impl.DefaultEndpoint;
import org.apache.camel.spi.UriEndpoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@UriEndpoint(firstVersion = "1.0.0", scheme = "mycustom", title = "MyCustomComponent", syntax = "mycustom:name", consumerClass = MyCustomConsumer.class, label = "custom")
public class MyCustomEndpoint extends DefaultEndpoint {
    private static final Logger LOG = LoggerFactory.getLogger(MyCustomEndpoint.class);
    private final String uri;

    private BlockingQueue<Exchange> queue = new LinkedBlockingQueue<>(5);

    public MyCustomEndpoint(String uri) {
        this.uri = uri;
    }

    public BlockingQueue<Exchange> getQueue() {
        return queue;
    }

    public void setQueue(BlockingQueue<Exchange> queue) {
        this.queue = queue;
    }

    @Override
    protected String createEndpointUri() {
        return uri;
    }

    @Override
    public Producer createProducer() throws Exception {
        return new MyCustomProducer(this);
    }

    @Override
    public Consumer createConsumer(Processor processor) throws Exception {
        LOG.info("Creating a default consumer");
        return new MyCustomConsumer(this, processor);
    }

    @Override
    public PollingConsumer createPollingConsumer() throws Exception {
        LOG.info("Creating a polling consumer");
        return super.createPollingConsumer();
    }

    @Override
    public boolean isSingleton() {
        return false;
    }

}
