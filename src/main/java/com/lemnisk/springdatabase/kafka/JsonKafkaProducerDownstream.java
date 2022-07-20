package com.lemnisk.springdatabase.kafka;

import java.util.List;
import java.util.Optional;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import com.lemnisk.springdatabase.entity.Customer;
import com.lemnisk.springdatabase.repository.ICustomerRepo;

@Service
public class JsonKafkaProducerDownstream {
		
	 private static final Logger LOGGER = LoggerFactory.getLogger(JsonKafkaProducerDownstream.class);
	    private KafkaTemplate<String, JSONObject> kafkaTemplate;


	    public JsonKafkaProducerDownstream(KafkaTemplate<String, JSONObject> kafkaTemplate) {
			this.kafkaTemplate = kafkaTemplate;
		}



		public void sendMessage1(JSONObject data){

	        LOGGER.info(String.format("Message sent -> %s", data.toString()));
	        Message<JSONObject> message=MessageBuilder
	                .withPayload(data)
	                .setHeader(KafkaHeaders.TOPIC, "bootcamp-project-1")
	                .build();
	        kafkaTemplate.send(message);
	    }

}