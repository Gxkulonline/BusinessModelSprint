package com.sprint.project.business_management_system.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sprint.project.business_management_system.Entity.Customer;
import com.sprint.project.business_management_system.Entity.Employee;
import com.sprint.project.business_management_system.Entity.Order;
import com.sprint.project.business_management_system.repository.CustomerRepository;
import com.sprint.project.business_management_system.repository.OrderRepository;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepo;

    @Autowired
    private OrderRepository orderRepo;

    // 1. Get customers by country
    public List<Customer> getCustomersByCountry(String country) {

        if (country == null || country.isBlank()) {
            throw new RuntimeException("Country cannot be empty");
        }

        return customerRepo.findByCountry(country);
    }

    // 2. Get top customers (by credit limit)
    public List<Customer> getTopCustomers() {
        return customerRepo.findAllByOrderByCreditLimitDesc();
    }

    // 3. Get orders by customer
    public List<Order> getOrdersByCustomer(Integer customerId) {

        Customer customer = customerRepo.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        return orderRepo.findByCustomer(customer);
    }

    // 4. Get orders by status
    public List<Order> getOrdersByCustomerIdAndStatus(Integer customerId, String status) {

        customerRepo.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        return orderRepo.findByCustomerCustomerNumberAndStatus(customerId, status);
    }

    // 5. Get support (sales rep)
    public Employee getCustomerSupport(Integer customerId) {

        Customer customer = customerRepo.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        Employee emp = customer.getSalesRep();

        if (emp == null) {
            throw new RuntimeException("No support assigned");
        }

        return emp;
    }
}