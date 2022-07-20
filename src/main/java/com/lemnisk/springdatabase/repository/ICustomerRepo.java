package com.lemnisk.springdatabase.repository;

import com.lemnisk.springdatabase.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ICustomerRepo extends JpaRepository<Customer, Long> {

    @Query("select p.engid from Customer p")
    List<Long> getAllIds();
    
    @Query(value="select body_template from Customer where engid=:id",nativeQuery=true)
    String getBodyTemplate(int id);
    
    @Query(value="select subject_template from Customer where engid=:id",nativeQuery=true)
    String getSubjectTemplate(int id);
    

}
