package com.lemnisk.springdatabase.Config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

    @Bean
    public NewTopic lemniskTopic(){
        return TopicBuilder.name("bootcamp-project")
                .build();
    }
    
    @Bean
    public NewTopic lemniskJsonTopic(){
        return TopicBuilder.name("bootcamp-project-1")
                .build();
    }


}

