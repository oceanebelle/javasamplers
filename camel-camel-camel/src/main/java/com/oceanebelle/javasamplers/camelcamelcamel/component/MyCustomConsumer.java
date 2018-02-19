package com.oceanebelle.javasamplers.camelcamelcamel.component;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.impl.DefaultConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;


public class MyCustomConsumer extends DefaultConsumer implements Runnable {
    private static final Logger LOG = LoggerFactory.getLogger(MyCustomConsumer.class);
    private final MyCustomEndpoint endpoint;
    private final Processor processor;

    private volatile boolean stop = false;

    private Thread thread;

    public MyCustomConsumer(MyCustomEndpoint endpoint, Processor processor) {
        super(endpoint, processor);

        this.endpoint = endpoint;
        LOG.info("CUSTOM CONSUMER: {}", processor);
        this.processor = processor;
    }

    @Override
    protected void doStop() throws Exception {
        super.doStop();

        LOG.info("STOPPING");

        stop = true;

    }

    @Override
    protected void doStart() throws Exception {
        super.doStart();

        LOG.info("STARTING");

        thread = new Thread(this);
        thread.setDaemon(true);
        thread.start();

    }

    @Override
    public void run() {
        LOG.info("Started consumer thread");
        try {
            while (!stop) {
                Exchange data = endpoint.getQueue().poll(100, TimeUnit.MILLISECONDS);
                if (data != null) {
                    LOG.info("READ: {}", data);
                    Exchange exh = getEndpoint().createExchange();
                    exh.getIn().setBody(data.getIn());
                    getProcessor().process(exh);
                }
            }
        } catch (Exception e) {
            LOG.error("Interrupted", e);
        } finally {
            LOG.info("Consumer thread done");
        }
    }
}
