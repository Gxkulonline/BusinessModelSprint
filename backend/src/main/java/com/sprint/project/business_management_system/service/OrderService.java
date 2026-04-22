package com.sprint.project.business_management_system.service;

import java.util.List;
import com.sprint.project.business_management_system.requestDto.OrderRequestDto;
import com.sprint.project.business_management_system.responseDto.OrderResponseDto;

public interface OrderService {

    OrderResponseDto createOrder(OrderRequestDto dto);

    OrderResponseDto getOrderById(Integer id);

    List<OrderResponseDto> getOrdersByStatus(String status);

    List<OrderResponseDto> getAllOrders();
}