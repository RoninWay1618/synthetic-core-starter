package com.wy.synthetic.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;

@Configuration
public class KafkaConfig {
    @Bean
    public NewTopic auditTopic() {
        return new NewTopic("audit_topic", 1, (short) 1);
    }
}
