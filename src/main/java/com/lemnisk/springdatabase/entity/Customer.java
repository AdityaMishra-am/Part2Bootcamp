package com.lemnisk.springdatabase.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Table(name="tbl_customer")
@Entity
@Setter
@Getter
@ToString
public class Customer {

    public String name;

    public String subjectTemplate;

    public String bodyTemplate;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public Long engid;

    //public String userName;

    //public String email;

    public String fromName;

    public String fromEmail;

    public String status="Not sent yet";

    public Customer() {
    }

    @CreationTimestamp
    @Column(name="created_at")
    private Date createdAt;

    @UpdateTimestamp
    @Column(name="updated_at")
    private Date updatedAt;

    public Long getEngid() {
        return engid;
    }

    public void setEngid(Long engid) {
        this.engid = engid;
    }



    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", subjectTemplate='" + subjectTemplate + '\'' +
                ", bodyTemplate='" + bodyTemplate + '\'' +
                ", engid=" + engid +
                ", fromName='" + fromName + '\'' +
                ", fromEmail='" + fromEmail + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}

