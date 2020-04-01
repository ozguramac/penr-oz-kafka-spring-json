package com.penroz.kafka.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;

@SpringBootApplication
public class Application implements ApplicationRunner {
    private static final Logger log = LoggerFactory.getLogger(Application.class);

    @Autowired
    private KafkaTemplate<String, PValue> kafkaTemplate;
    @Value("${penroz.numOfTestMgs}")
    private Integer numOfTestMsgs;
    @Autowired
    private PValueBuilder valueBuilder;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @KafkaListener(topics = "${spring.kafka.template.default-topic}")
    public void listen(final Object gvalue) {
        log.info("Received Message: {}", gvalue);
    }

    @Override
    public void run(final ApplicationArguments args) throws Exception {
        log.info("Running....Sending message(s)...");
        for (int i = 0; i < numOfTestMsgs; i++) {
            final PValue value = valueBuilder.create();
            value.setId(UUID.randomUUID().toString());
            value.setMessage("Penr-oz Test " + i + ": Connect to Kafka from Spring! Yeah :-D");
            kafkaTemplate.sendDefault(value.getId(), value);
            Thread.sleep(1000);
        }
    }

    public interface PValue {
        String getId();

        void setId(String id);

        String getMessage();

        void setMessage(String message);
    }

    public interface PValueBuilder {
        PValue create();
    }

    private static class PJsonValue implements PValue {
        private String id, message;

        @Override
        public String getId() {
            return id;
        }

        @Override
        public void setId(String id) {
            this.id = id;
        }

        @Override
        public String getMessage() {
            return message;
        }

        @Override
        public void setMessage(String message) {
            this.message = message;
        }

        @Override
        public String toString() {
            return "{" +
                    "id='" + id + '\'' +
                    ", message='" + message + '\'' +
                    '}';
        }
    }

    @Component
    private static class PJsonValueBuilder implements PValueBuilder {
        public PValue create() {
            return new PJsonValue();
        }
    }
}
