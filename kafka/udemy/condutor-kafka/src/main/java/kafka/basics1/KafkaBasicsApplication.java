package kafka.basics1;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class KafkaBasicsApplication {
    public static final Logger log = LoggerFactory.getLogger(KafkaBasicsApplication.class.getName());
    public static void main(String[] args) throws InterruptedException {
        log.info("Starting producer");

        Properties kafkaProducerProperties = new Properties();
        kafkaProducerProperties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        kafkaProducerProperties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        kafkaProducerProperties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        KafkaProducer<String, String> kafkaProducer = new KafkaProducer<>(kafkaProducerProperties);

        for(int i=0;i<100;i++) {
            ProducerRecord<String,String> producerRecord = new ProducerRecord<>("test_topic_1", "kafka message:"+(i+1));

            log.info("Attempting to send message id {}", i);
            kafkaProducer.send(producerRecord, (recordMetadata, e) -> {
                if(e == null) {
                    log.info("Sent message successfully offset:{} toString:{} partition:{} topic:{}",
                            recordMetadata.offset(),
                            recordMetadata,
                            recordMetadata.partition(),
                            recordMetadata.topic());
                    return;
                }

                log.error("Failed to send message to topic error:{} stacktrace:{} cause:{}", e.getMessage(), e.getStackTrace(),e.getCause());
            });

            if(i%4==0) {
                Thread.sleep(200);
            }
        }

        kafkaProducer.flush();

        kafkaProducer.close();
    }
}
