package com.lemnisk.springdatabase.controller;

import com.lemnisk.springdatabase.entity.Customer;
import com.lemnisk.springdatabase.kafka.JsonKafkaProducer;
import com.lemnisk.springdatabase.repository.ICustomerRepo;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class JsonMessageController {

    @Autowired
    private ICustomerRepo iCustomerRepo;
    private JsonKafkaProducer kafkaProducer;

    public JsonMessageController(JsonKafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }

    @GetMapping("/publish/")
    public ResponseEntity<String> publish(){

        List<Long> idList= iCustomerRepo.getAllIds();
//        for(Long id : idList)
//        {
//            Optional<Customer> customer = iCustomerRepo.findById(id);
//            JSONObject obj = new JSONObject();
//            obj.put("userName",customer.get().getUserName());
//            obj.put("engID", customer.get().getEngid());
//            obj.put("email",customer.get().getEmail());
//            kafkaProducer.sendMessage(obj);
//
//
//        }
        return ResponseEntity.ok("JSON Message sent to Kafka Topic");


    }

}
