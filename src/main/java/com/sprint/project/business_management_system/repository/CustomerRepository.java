package com.sprint.project.business_management_system.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
<<<<<<< HEAD
import org.springframework.data.repository.query.Param;
=======
>>>>>>> refs/heads/master

import com.sprint.project.business_management_system.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    List<Customer> findByCountry(String country);
    List<Customer> findAllByOrderByCreditLimitDesc();
<<<<<<< HEAD
    @Query("SELECT c FROM Customer c WHERE c.creditLimit > :limit")
    List<Customer> findHighCreditCustomers(@Param("limit") double limit);
=======
//     @Query("SELECT c FROM Customer c WHERE c.name=:name")
//    Customer getCustomerByName(String name);
>>>>>>> refs/heads/master

}
