package javasamplers.apps.kafkaadventures.app;


import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotNull;

/**
 * Test the context is valid
 */
@Ignore
@RunWith(SpringRunner.class)
@SpringBootTest
public class ContextLoadingTest {

    //@ClassRule
    //public static KafkaEmbedded embeddedKafka = new KafkaEmbedded(1, true, "topic");

    @Autowired
    AppConfiguration config;

    @BeforeClass
    public static void setup() {
        // TODO: Automatic way of getting this property in?
        //System.setProperty("spring.kafka.bootstrap-servers", embeddedKafka.getBrokersAsString());
        //System.setProperty("spring.cloud.stream.kafka.binder.zkNodes", embeddedKafka.getZookeeperConnectionString());
    }

    @Test
    public void testLoadContext() {
        assertNotNull(config);
    }
}
