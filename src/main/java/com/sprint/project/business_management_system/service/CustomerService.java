package com.sprint.project.business_management_system.service;

import java.util.List;

import com.sprint.project.business_management_system.entity.Customer;
import com.sprint.project.business_management_system.entity.Employee;
import com.sprint.project.business_management_system.entity.Order;

public interface CustomerService {

    List<Customer> getCustomersByCountry(String country);
    
    List<Customer> getTopCustomers();

    List<Order> getOrdersByCustomer(Integer customerId);

    List<Order> getOrdersByCustomerIdAndStatus(Integer customerId, String status);

    Employee getCustomerSupport(Integer customerId);
    List<Customer> getAllCustomers();

    Customer getCustomerById(Integer id);

    Customer saveCustomer(Customer customer);

    void deleteCustomer(Integer id);
}