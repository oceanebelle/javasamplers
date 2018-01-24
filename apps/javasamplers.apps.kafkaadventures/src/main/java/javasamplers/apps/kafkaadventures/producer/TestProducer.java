package javasamplers.apps.kafkaadventures.producer;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;

public class TestProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final String topic;

    private static final Logger Log = LoggerFactory.getLogger(TestProducer.class);

    public TestProducer(KafkaTemplate<String, String> kafkaTemplate, String topic) {
        this.kafkaTemplate = kafkaTemplate;
        this.topic = topic;
    }

    public void send(String key, String message) {
        // We don't care about the response.
        Log.info("Sending {}:{}", key, message);
        kafkaTemplate.send(topic, key, message);
    }
}
