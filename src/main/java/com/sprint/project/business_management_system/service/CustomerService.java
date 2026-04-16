package com.sprint.project.business_management_system.service;

import java.util.List;

import com.sprint.project.business_management_system.Entity.Customer;
import com.sprint.project.business_management_system.Entity.Employee;
import com.sprint.project.business_management_system.Entity.Order;

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