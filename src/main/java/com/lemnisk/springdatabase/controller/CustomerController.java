package com.lemnisk.springdatabase.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

import com.lemnisk.springdatabase.creationrequest.PostRequest;
import com.lemnisk.springdatabase.entity.Customer;
import com.lemnisk.springdatabase.entity.UserList;
import com.lemnisk.springdatabase.kafka.JsonKafkaProducer;
import com.lemnisk.springdatabase.repository.ICustomerRepo;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;




@RestController
public class CustomerController {

    private JsonKafkaProducer kafkaProducer;
    public CustomerController(JsonKafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }

    @Autowired
    ICustomerRepo customerRepo;

    public static boolean isValid(String email)
    {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }

    @PostMapping("/customers")
    public ResponseEntity<String> save(@RequestBody PostRequest postRequest){


//       try {
//            if (userList.getUserName().isEmpty())
//            {
//                return ResponseEntity.badRequest().body("User Name Cannot be empty");
//            }
//            else if (userList.getEmail().isEmpty())
//            {
//                return ResponseEntity.badRequest().body("Email Cannot be empty");
//            }
//            else
//            {
//                customerRepo.save(customer1);
//                JSONObject obj = new JSONObject();
//                obj.put("userName",userList.getUserName());
//                obj.put("engID", customer1.getEngid());
//                obj.put("email",userList.getEmail());
//                System.out.println(obj);
//                userL.add(obj);
//                kafkaProducer.sendMessage(obj);
//
//                return ResponseEntity.ok("JSON Message sent to Kafka Topic");
//            }
        try{
            //List<UserList> userList = postRequest.getUserList();
            List<UserList> userList = postRequest.getUserList();
            Customer customer1 = postRequest.getCustomer();
            List<JSONObject> userL = new ArrayList<>();
            for(int i = 0; i<userList.size();++i)
            {
                if (userList.get(i).getUserName().isEmpty())
                {
                    return ResponseEntity.badRequest().body("User Name "+(i+1)+" Cannot be empty");
                }
                else if (userList.get(i).getEmail().isEmpty())
                {
                    return ResponseEntity.badRequest().body("Email "+(i+1)+" Cannot be empty");
                }
                else if(!isValid(userList.get(i).getEmail()))
                {
                    return ResponseEntity.badRequest().body("Email "+(i+1)+" Invalid");
                }


            }
            customerRepo.save(customer1);
            for(int i = 0; i<userList.size();++i)
            {
                JSONObject obj = new JSONObject();
                obj.put("userName",userList.get(i).getUserName());
                obj.put("engID", customer1.getEngid());
                obj.put("email",userList.get(i).getEmail());
                kafkaProducer.sendMessage(obj);
            }
            return ResponseEntity.ok("JSON Message sent to Kafka Topic");

        } catch(Exception e) {
            return ResponseEntity.ok("JSON Message not sent to Kafka Topic");
        }
    }

    @GetMapping("/customers")
    public ResponseEntity<List<Customer>> getAllCustomers(){
        try {
            List<Customer> list = customerRepo.findAll();
            if(list.isEmpty() || list.size()==0) {
                return new ResponseEntity<List<Customer>>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<List<Customer>>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


//    @GetMapping("/customers/{id}")
//    public JSONObject getAllCustomers(@PathVariable Long id){
//        Optional<Customer> customer = customerRepo.findById(id);
////        customer.get().getEmail();
////        customer.get().getName();
////        customer.get().getEngid();
//        JSONObject notfound = new JSONObject();
//        notfound.put("Error", "Not Found");
//        JSONObject obj = new JSONObject();
//        obj.put("userName",customer.get().getUserName());
//        obj.put("engID", new Long(customer.get().getEngid()));
//        obj.put("email",customer.get().getEmail());
//        //String jsonobject = "name:"+customer.get().getName()+", endIDL:"+customer.get().getEngid()+"email:"+customer.get().getEmail();
//
//        if(customer.isPresent()) {
//            return obj;
//        }
//        else
//            return notfound;
//
//        //return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
//    }


    //public List<String> getCustomers(@PathVariable)

}
