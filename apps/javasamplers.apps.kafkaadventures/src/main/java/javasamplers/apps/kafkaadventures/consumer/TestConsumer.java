package javasamplers.apps.kafkaadventures.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;


public class TestConsumer {

    private static final Logger Log = LoggerFactory.getLogger(TestConsumer.class);

    @KafkaListener(topics = "test", id="test")
    public void listen(ConsumerRecord<String, String> record) {
        Log.info("RECEIVED << {}: {}", record.key(), record.value());
    }
}
