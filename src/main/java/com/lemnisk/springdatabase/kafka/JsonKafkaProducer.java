package com.lemnisk.springdatabase.kafka;

import com.lemnisk.springdatabase.entity.Customer;
import com.lemnisk.springdatabase.repository.ICustomerRepo;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JsonKafkaProducer {



    private static final Logger LOGGER = LoggerFactory.getLogger(JsonKafkaProducer.class);
    private KafkaTemplate<String, JSONObject> kafkaTemplate;

    public JsonKafkaProducer(KafkaTemplate<String, JSONObject> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(JSONObject data){

        LOGGER.info(String.format("Message sent -> %s", data.toString()));
        Message<JSONObject> message=MessageBuilder
                .withPayload(data)
                .setHeader(KafkaHeaders.TOPIC, "bootcamp-project")
                .build();
        kafkaTemplate.send(message);
    }
}