package com.oceanebelle.javasamplers.camel.routes.config;

import com.oceanebelle.javasamplers.camel.routes.controller.AppController;
import org.apache.camel.CamelContext;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotNull;

@SpringBootTest(classes = {AppConfig.class})
@RunWith(SpringRunner.class)
public class ContextLoadTest {

    @Autowired
    AppController controller;

    @Autowired
    CamelContext camelContext;

    @Test
    public void ensureSpringLoads() {
        assertNotNull(camelContext);
        assertNotNull(controller);
    }
}
