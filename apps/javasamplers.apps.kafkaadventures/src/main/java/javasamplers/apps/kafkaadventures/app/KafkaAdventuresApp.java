package javasamplers.apps.kafkaadventures.app;

import javasamplers.apps.kafkaadventures.producer.TestProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KafkaAdventuresApp implements ApplicationRunner {

    private static final Logger Log = LoggerFactory.getLogger(KafkaAdventuresApp.class);

    @Autowired
    private TestProducer producer;

    /**
     * Entrypoint
     * @param args
     */
    public static void main(String... args) {
        SpringApplication.run(KafkaAdventuresApp.class, args);
    }


    @Override
    public void run(ApplicationArguments args) throws Exception {
        Log.info("Application started");



        producer.send("1", "first");
        producer.send("2", "second");
        producer.send("3", "third");

    }
}
