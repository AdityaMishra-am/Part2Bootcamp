package com.lemnisk.springdatabase.kafka;

import java.util.List;
import java.util.Optional;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.lemnisk.springdatabase.entity.Customer;
import com.lemnisk.springdatabase.repository.ICustomerRepo;

@Service
public class JsonKafkaConsumer {

	@Autowired
    ICustomerRepo iCustomerRepo;

	 private JsonKafkaProducerDownstream kafkaProducer;


		public JsonKafkaConsumer(JsonKafkaProducerDownstream kafkaProducer) {
		this.kafkaProducer = kafkaProducer;
	}


		private static final Logger LOGGER = LoggerFactory.getLogger(JsonKafkaConsumer.class);


		@KafkaListener(topics = "bootcamp-project", groupId = "myGroup")
		public void consume(JSONObject user) {
			LOGGER.info(String.format("Json message received -> %s",user.toString()));
			//List<Long> idList= iCustomerRepo.getAllIds();
			System.out.print(user.get("engID").toString());
			String id = user.get("engID").toString();
			long id1 = Long.parseLong(id);
			System.out.println("Printing Long "+id1);
			Optional<Customer> customer = iCustomerRepo.findById(id1);

			JSONObject obj = new JSONObject();
			obj.put("subject_template", customer.get().getSubjectTemplate().replace("${userName}", (String)user.get("userName")));
			obj.put("body_template", customer.get().getBodyTemplate().replace("${email}",(String)user.get("email")));
			obj.put("email",(String)user.get("email"));
			kafkaProducer.sendMessage1(obj);
	        System.out.print("Kafka message sent");

//			int id1=(int) user.get("engID");
//			///Long id1 =Long.parseLong(id);
//	             Optional<Customer> customerOpt = iCustomerRepo.findById((long) id1);
//	             Customer customer= customerOpt.get();
//	             JSONObject obj = new JSONObject();
//	             obj.put("subject_template", customer.subjectTemplate.replace("${userName}",(String)user.get("userName")));
//	             obj.put("body_template", customer.bodyTemplate.replace("${email}",(String)user.get("email")));
//	             //obj.put("subject_template","Hey"+" "+customer.get().getUserName());
//	             //obj.put("body_template", "Hey"+" "+customer.get().getEmail()+" "+"10% off on all products");
//	             obj.put("email",user.get("email"));
//	             kafkaProducer.sendMessage(obj);
//	             System.out.print("Kafka message sent");


	         }
}







