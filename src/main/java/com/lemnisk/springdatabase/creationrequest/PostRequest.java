package com.lemnisk.springdatabase.creationrequest;

import com.lemnisk.springdatabase.entity.Customer;
import com.lemnisk.springdatabase.entity.UserList;

import java.util.List;

public class PostRequest {
    private List<UserList> userList;
    private Customer customer;

    public List<UserList> getUserList() {
        return userList;
    }

//    private  UserList userList;

//    public UserList getUserList() {
//        return userList;
//    }

    public Customer getCustomer() {
        return customer;
    }

    public PostRequest(List<UserList> userList, Customer customer) {
        this.userList = userList;
        this.customer = customer;
    }
}
