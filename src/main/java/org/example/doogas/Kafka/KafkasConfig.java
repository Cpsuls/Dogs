package org.example.doogas.Kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
@Configuration
public class KafkasConfig {

    @Bean
    NewTopic topic(){
        return TopicBuilder.name("doogas").
                partitions(1).replicas(1).
                build();
    }
}

