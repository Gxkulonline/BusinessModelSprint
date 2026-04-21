package com.sprint.project.business_management_system.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sprint.project.business_management_system.Entity.Customer;
import com.sprint.project.business_management_system.Entity.Order;
import com.sprint.project.business_management_system.repository.CustomerRepository;
import com.sprint.project.business_management_system.repository.OrderRepository;
import com.sprint.project.business_management_system.requestDto.OrderRequestDto;
import com.sprint.project.business_management_system.responseDto.OrderResponseDto;
import com.sprint.project.business_management_system.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepo;

    @Autowired
    private CustomerRepository customerRepo;

    private Order mapToEntity(OrderRequestDto dto) {

        Order order = new Order();

        order.setOrderNumber(dto.getOrderNumber());
        order.setOrderDate(dto.getOrderDate());
        order.setRequiredDate(dto.getRequiredDate());
        order.setShippedDate(dto.getShippedDate());
        order.setStatus(dto.getStatus());
        order.setComments(dto.getComments());

        Customer customer = customerRepo.findById(
                dto.getCustomer().getCustomerNumber()
        ).orElseThrow();

        order.setCustomer(customer);

        return order;
    }

    private OrderResponseDto mapToDto(Order o) {
        OrderResponseDto dto = new OrderResponseDto();

        dto.setOrderNumber(o.getOrderNumber());
        dto.setOrderDate(o.getOrderDate());
        dto.setStatus(o.getStatus());

        return dto;
    }

    public OrderResponseDto createOrder(OrderRequestDto dto) {
        return mapToDto(orderRepo.save(mapToEntity(dto)));
    }

    public OrderResponseDto getOrderById(Integer id) {
        return mapToDto(orderRepo.findById(id).orElseThrow());
    }

    public List<OrderResponseDto> getOrdersByStatus(String status) {
        return orderRepo.findByStatus(status)
                .stream()
                .map(this::mapToDto)
                .toList();
    }
}